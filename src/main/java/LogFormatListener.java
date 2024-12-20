package com.shinemo.common.tools.config;

import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.OutputStreamAppender;

public class LogFormatListener implements ApplicationListener<ApplicationEvent> {

    private static final String LOGBACK_PATTERN = "%level %thread %d{yyyy-MM-dd HH:mm:ss.SSS} %class{35}.%method - %msg%n";
    private static final String LOG4J_PATTERN = "%level %t %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1.}.%M(%L) - %m%n";

    @Override
    public void onApplicationEvent(ApplicationEvent arg0) {
        try {
            if (Class.forName("ch.qos.logback.classic.Logger") != null
                    && LoggerFactory.getLogger("root") instanceof ch.qos.logback.classic.Logger) {
                // logback
                try {
                    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                    List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
                    for (ch.qos.logback.classic.Logger logger : loggerList) {
                        Iterator<Appender<ILoggingEvent>> appenderList = logger.iteratorForAppenders();
                        while (appenderList.hasNext()) {
                            OutputStreamAppender<ILoggingEvent> configAppender = (OutputStreamAppender<ILoggingEvent>) appenderList
                                    .next();
                            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
                            encoder.setPattern(LOGBACK_PATTERN);
                            encoder.setContext(loggerContext);
                            encoder.start();
                            configAppender.setEncoder(encoder);
                            configAppender.start();
                        }
                    }
                    loggerContext.start();
                } catch (Throwable e) {
                    System.out.println("LogFormatListener");
                    e.printStackTrace();
                }
            } else {
                // TODO log4j
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}