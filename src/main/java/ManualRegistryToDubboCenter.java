import cn.hutool.core.net.NetUtil;
import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.UrlUtils;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.remoting.etcd.Constants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author wuzhenhong
 * @date 2023/12/22 15:06
 */
@Component
public class ManualRegistryToDubboCenter implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    @Value("${server.port}")
    private Integer port;

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, org.apache.dubbo.config.RegistryConfig> registryConfigMap = applicationContext.getBeansOfType(RegistryConfig.class);
        if(Objects.isNull(registryConfigMap) || registryConfigMap.isEmpty()) {
            return;
        }
        RegistryConfig registryConfig = registryConfigMap.entrySet().iterator().next().getValue();
        RegistryProtocol registryProtocol = RegistryProtocol.getRegistryProtocol();
        Field field = ReflectionUtils.findField(RegistryProtocol.class, "registryFactory");
        field.setAccessible(true);
        RegistryFactory registryFactory;
        try {
            registryFactory = (RegistryFactory)field.get(registryProtocol);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // String protocol, String host, int port, String path
        // zookeeper://192.168.203.233:2181
        List<URL> urls = UrlUtils.parseURLs(registryConfig.getAddress(), Maps.newHashMap());
        Registry registry = registryFactory.getRegistry(urls.get(0));

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        handlerMethodMap.values().stream().forEach(handlerMethod -> {
            RequestMapping cl = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RequestMapping.class);
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequestMapping.class);
            URL url = new URL("dubbo", NetUtil.getLocalhostStr(), port, requestMapping.path()[0]);
            registry.register(url);
            List<URL> consumerList = registry.lookup(UrlUtils.getEmptyUrl(requestMapping.path()[0], Constants.PROVIDERS_CATEGORY));
            Optional.ofNullable(consumerList)
                .orElse(Collections.emptyList())
                .stream().forEach(e -> {
                    System.out.println("url:" + e.getPath() + " host:" + e.getIp() + "   port:"+e.getPort());
                });
        });

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
