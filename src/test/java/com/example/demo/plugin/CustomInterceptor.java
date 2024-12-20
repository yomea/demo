package com.example.demo.plugin;

import com.example.demo.plugin.CustomResultSetHandler;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(
    {
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
    }
)
public class CustomInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            MetaObject metaObject = MetaObject.forObject(parameterHandler, new DefaultObjectFactory(),
                new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
            MappedStatement ms = (MappedStatement)metaObject.getValue("mappedStatement");
            SqlCommandType sqlCommandType = ms.getSqlCommandType();
            if(SqlCommandType.INSERT == sqlCommandType
                || SqlCommandType.UPDATE == sqlCommandType) {
                BoundSql boundSql = (BoundSql)metaObject.getValue("boundSql");
                Configuration configuration = (Configuration)metaObject.getValue("configuration");
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                Object parameterObject = boundSql.getParameterObject();
                MetaObject metaParameterObject = configuration.newMetaObject(parameterObject);
                parameterMappings.forEach(e -> {
                    Object val = metaParameterObject.getValue(e.getProperty());
                    //TODO：判断是否是需要加密的字段，如果是加密，这里的逻辑你先想好怎么操作
                    metaParameterObject.setValue(e.getProperty(), val);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof DefaultResultSetHandler) {
            DefaultResultSetHandler setHandler = (DefaultResultSetHandler) target;
            MetaObject metaObject = MetaObject.forObject(setHandler, new DefaultObjectFactory(),
                new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
            Executor executor = (Executor) metaObject.getValue("executor");
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
            RowBounds rowBounds = (RowBounds) metaObject.getValue("rowBounds");
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("parameterHandler");
            BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
            ResultHandler<?> resultHandler = (ResultHandler) metaObject.getValue("resultHandler");
            // Executor executor, MappedStatement mappedStatement, ParameterHandler parameterHandler, ResultHandler<?> resultHandler, BoundSql boundSql,
            //                                 RowBounds rowBounds
            CustomResultSetHandler handler = new CustomResultSetHandler(
                executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds
            );
            return handler;
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 把你的配置加载进来
    }
}
