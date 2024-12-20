package com.example.demo.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.ConverterUtil;
import ch.qos.logback.core.pattern.parser.Node;
import ch.qos.logback.core.pattern.parser.Parser;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * @author zhen_hong
 * @date 2022/8/9 11:21
 */
public class LogbackDemo3Test {

    public static void main(String[] args) throws Exception {
        // 绑定日志实现，这里使用的是logback实现并解析配置，如果存在logback.xml，logback.groovy and so on 或者系统属性中有指定对应的路径
        ILoggerFactory loggerContext = StaticLoggerBinder.getSingleton().getLoggerFactory();

        // 使用logback解析器解析pattern
        Parser<ILoggingEvent> p = new Parser<>("%d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n");
        p.setContext((LoggerContext)loggerContext);
        Node t = p.parse();
        // 转换器，根据pattern中的%d,%logger创建
        Map<String, String> converterMap = new HashMap<>();
        converterMap.putAll(PatternLayout.DEFAULT_CONVERTER_MAP);

        // 编译转换器，用于处理形如 %d{xxx} 的占位符
        Converter c = p.compile(t, converterMap);

        StringBuilder buf = new StringBuilder();

        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);

        // 构建一个日志事件
        ILoggingEvent event = new LoggingEvent(LogbackTest.class.getName(), logger, Level.INFO
            , "这个是一个测试方法", null, null);
        ((LoggingEvent) event).setTimeStamp(System.currentTimeMillis());

        ConverterUtil.startConverters(c);
        while (c != null) {
            c.write(buf, event);
            c = c.getNext();
        }
        System.out.println(buf.toString());
    }

}