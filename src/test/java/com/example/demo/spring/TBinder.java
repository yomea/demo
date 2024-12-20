package com.example.demo.spring;

import java.util.function.Consumer;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * @author wuzhenhong
 * @date 2024/2/5 10:36
 */
public class TBinder {

    public static void main(String[] args) {
        // RelaxedPropertyResolver
        StandardEnvironment environment = new StandardEnvironment();
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        PropertySourcesPlaceholdersResolver placeholdersResolver = new PropertySourcesPlaceholdersResolver(environment);
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        Binder binder = new Binder(sources, placeholdersResolver, conversionService, (Consumer)null, (BindHandler)null);
        MyProperties target = binder
            .bind("user", MyProperties.class)
            .orElse(null);
        System.out.println(target);
    }

}
