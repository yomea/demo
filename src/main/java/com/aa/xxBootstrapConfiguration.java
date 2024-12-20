package com.aa;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;

/**
 *
 * 第一种：在nacos上配置
 spring.cloud.config.allowOverride: true
 spring.cloud.config.overrideNone: true

 第二种：
 在resources下新增一个META-INF目录，里面放一个spring.factories，内容demo如下：
 org.springframework.cloud.bootstrap.BootstrapConfiguration=\
 com.aa.xxBootstrapConfiguration

 xxBootstrapConfiguration代码如下：

 @Configuration(proxyBeanMethods = false)
 public class xxBootstrapConfiguration {

 @Bean("hahaLocator")
 public PropertySourceLocator nacosPropertySourceLocator() {
 return environment -> {
 try {
 List<PropertySource<?>> propertySources = NacosDataParserHandler.getInstance()
 .parseNacosData("test", "spring.cloud.config.allowOverride: true\n"
 + "spring.cloud.config.overrideNone: true",
 "yml");
 CompositePropertySource compositePropertySource = new CompositePropertySource(
 "hahaCompositePropertySource");
 propertySources.stream().forEach(ps -> compositePropertySource.addPropertySource(ps));
 return compositePropertySource;
 } catch (IOException e) {
 throw new RuntimeException(e);
 }
 };
 }
 }*/
@Configuration(proxyBeanMethods = false)
public class xxBootstrapConfiguration {

    private String key1 = "spring.cloud.config.allowOverride";
    private String key2 = "spring.cloud.config.overrideNone";

    @Bean("hahaLocator")
    public PropertySourceLocator nacosPropertySourceLocator() {
        return environment -> {
            Map<String, Object> map = new HashMap<>();
            if(environment.containsProperty(key1)) {
                map.put(key1, environment.getProperty(key1));
            }
            if(environment.containsProperty(key2)) {
                map.put(key2, environment.getProperty(key2));
            }
            MapPropertySource mapPropertySource = new MapPropertySource("mapPropertySource", map);
            return mapPropertySource;
        };
    }
}
