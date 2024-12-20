package com.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import java.util.Properties;

/**
 * @author wuzhenhong
 * @date 2024/11/8 17:22
 */
public class T {

    public static void main(String[] args) {
        try {
            String serverAddr = "{serverAddr}";
            String dataId = "{dataId}";
            String group = "{group}";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            e.printStackTrace();
        }try {
            String serverAddr = "{serverAddr}";
            String dataId = "{dataId}";
            String group = "{group}";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

}
