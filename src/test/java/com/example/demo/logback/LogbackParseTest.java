package com.example.demo.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import java.net.URL;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @author wuzhenhong
 * @date 2022/8/9 11:21
 */
public class LogbackParseTest {

    public static void main(String[] args) throws Exception {
        // 绑定日志实现，这里使用的是logback实现并解析配置，如果存在logback.xml，logback.groovy and so on 或者系统属性中有指定对应的路径
//        ILoggerFactory loggerContext = StaticLoggerBinder.getSingleton().getLoggerFactory();

        ILoggerFactory loggerContext = LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        // 清除默认的基本 appender 配置
        rootLogger.detachAndStopAllAppenders();

        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext((LoggerContext)loggerContext);
        URL url = Thread.currentThread().getContextClassLoader().getResource("logback-custom.xml");
        configurator.doConfigure(url);

        org.slf4j.Logger logger = loggerContext.getLogger(LogbackParseTest.class.getName());
        Marker marker = MarkerFactory.getMarker("测试日志");

        logger.info(marker, "这个是一个测试方法");
    }

}
