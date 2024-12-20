package com.example.demo.sulaoban;
import cn.hutool.json.JSONUtil;
import com.example.demo.Address;
import com.example.demo.User;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {
    Statement.class})})
public class FieldEncryptInterceptor4 implements Interceptor {

    private static final List<Class<?>> CLASS_LIST = Arrays
        .asList(Object.class, String.class, BigDecimal.class, double.class, Double.class
            , int.class, Integer.class, long.class, Long.class, float.class, Float.class, short.class, Short.class
            , byte.class, Byte.class, boolean.class, Boolean.class);

    private static final ObjectFactory OBJECT_FACTORY = new DefaultObjectFactory();
    private static final org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ResultSetHandler resultSetHandler = (ResultSetHandler) invocation.getTarget();
//        MappedStatement mappedStatement = (MappedStatement) parameterHandler.getClass().getDeclaredField("mappedStatement").get(parameterHandler);
        MetaObject metaObject = MetaObject.forObject(resultSetHandler, OBJECT_FACTORY, OBJECT_WRAPPER_FACTORY, REFLECTOR_FACTORY);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 只处理dml语句
        if(SqlCommandType.INSERT == sqlCommandType
            || SqlCommandType.UPDATE == sqlCommandType) {
            try {
                List<FieldEncryptSnapshotInfo> snapshotInfos = ThreadLocalUtil.get();
                snapshotInfos.stream().forEach(e -> {
                    e.getField().set(e.getParameter(), e.getOrigin());
                });
            } finally {
                ThreadLocalUtil.remove();
            }
        }
        return invocation.proceed();
    }

    public static void main(String[] args) throws IllegalAccessException {
        FieldEncryptInterceptor3 interceptor2 = new FieldEncryptInterceptor3();

        User user = new User();
        user.setName("苏城锋");
        Address address = new Address();
        address.setXxx("萧山");
        user.setAddress(address);

        User user2 = new User();

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        ParamMap<Object> map = new ParamMap<>();
        map.put("collection", list);
        map.put("list", list);
        map.put("userList", list);

        interceptor2.execEncrypt(map);

        System.out.println(JSONUtil.toJsonStr(list));
    }

    private void execEncrypt(Object parameter) throws IllegalAccessException {
        if (Objects.isNull(parameter)) {
            return;
        }
        if(parameter instanceof Map) {
            Map map = (Map)parameter;
            List<Object> itemRepeatList = new ArrayList<>();
            map.values().forEach(item -> {
                for(Object repeat : itemRepeatList) {
                    if(repeat == item) {
                        return;
                    }
                }
                this.doGetEncryptVal(item, null);
                itemRepeatList.add(item);
            });
        } else {
            this.doGetEncryptVal(parameter, null);
//                    this.process(item);
        }
    }

    //只处理bean
    private void process(Object parameter) {
        if (Objects.isNull(parameter)) {
            return;
        }
        Class<?> clazz = parameter.getClass();
        if (CLASS_LIST.contains(clazz) || clazz.getName().startsWith("java.lang")) {
            return;
        }
        // 只处理java bean，这种字段上才可能存在注解
        List<Field> fieldList = this.getFieldList(clazz);
        this.doProcess(parameter, fieldList);

    }

    public List<Field> getFieldList(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        this.getFields(fieldList, clazz);
        return fieldList;
    }

    private void doProcess(Object bean, List<Field> fieldList) {

        fieldList.stream().forEach(field -> {

            field.setAccessible(true);
            try {
                field.set(bean, this.getEncryptVal(bean, field));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("方法不允许访问！");
            }
        });
    }

    private Object getEncryptVal(Object parameter, Field field) throws IllegalAccessException {
        Object fieldBean = field.get(parameter);
        if (fieldBean == null) {
            // 没有值，不需要操作
            return null;
        }
        return this.doGetEncryptVal(fieldBean, field);
    }

    private Object doGetEncryptVal(Object fieldBean, Field field) {
        if(Objects.isNull(fieldBean)) {
            return null;
        }
        // 字段类型
        Class<?> clazz = fieldBean.getClass();
        // 只对标有注解的String类型java bean字段做加密
        if (clazz.isArray()) {
            Object[] c = (Object[]) fieldBean;
            for(Object item : c) {
                this.doGetEncryptVal(item, null);
            }
        } else if (Iterable.class.isAssignableFrom(clazz)) {
            Iterable c = (Iterable) fieldBean;
            for(Object item : c) {
                this.doGetEncryptVal(item, null);
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map)fieldBean;
            map.values().stream().forEach(item -> {
                this.doGetEncryptVal(item, null);
            });
        } else if (String.class.isAssignableFrom(clazz)) {
            boolean encrypt = this.isEncrypt(field);
            return this.encryptNess( (String) fieldBean, encrypt);
        } else {
            this.process(fieldBean);
        }
        return fieldBean;
    }

    private Object encryptNess(String fieldBean, boolean encrypt) {
        if(!encrypt) {
            return fieldBean;
        }
        String encryptedValue = fieldBean + "java --苏*锋";
        return encryptedValue;
    }

    private boolean isEncrypt(Field field) {
        if(Objects.isNull(field)) {
            return false;
        }
        Crypto cryptoAnnotation = field.getAnnotation(Crypto.class);
        if (cryptoAnnotation != null) {
            return true;
        }
        return false;
    }

    private void getFields(List<Field> fieldList, Class<?> tClass) {

        if (tClass == null || tClass == Object.class || tClass.getName().startsWith("java.lang")) {
            return;
        }
        Field[] fields = tClass.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    fieldList.add(field);
                }

            }
        }
        this.getFields(fieldList, tClass.getSuperclass());
    }
}