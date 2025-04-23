package com.example.demo.junit;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

/**
 * @author wuzhenhong
 * @date 2024/7/12 9:26
 */
public class DelFeeApplyTTTTT {

    @Test
    public void t() {

        String s = "16927,17028";
        Arrays.stream(s.split(",")).forEach(id -> {
            Map<String, Object> pa = new HashMap<>();
            pa.put("id", id);
            doPostRequest("http://erp.sxcigtrade.com/platform/api", "delete_fm_fee_apply", "sbPiP8u7", pa);
        });

    }

    @Test
    public void sdfsdf() {
        System.out.println(Integer.parseInt("0001"));
    }

    public static String doPostRequest(String url, String command, String token, Object params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000)
                .setRedirectsEnabled(true).build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("saasToken", token);
            httpPost.setHeader("webToken", token);
            httpPost.setHeader("content-Type", "application/json;charset=UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("data", params);
            map.put("command", command);
            map.put("accesstoken", "accesstoken");
            map.put("timestamp", System.currentTimeMillis());
            map.put("version", "1.0");
            httpPost.setEntity(new StringEntity(
                JSONObject.toJSONString(map), ContentType.create("application/json", StandardCharsets.UTF_8)));
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine().getStatusCode() != 200) {
                throw new Exception("异常，URL为：" + url + "，参数为：" + params);
            }
            //            log.info("响应内容为:{}", result);
//            JSONObject object = JSONObject.parseObject(result);
            //if(object.getInteger("code") != 200) {
            //    log.error(String.format(ERROR_STR, url, params, result));
            //    throw new Exception(String.format(ERROR_STR, url, params, result));
            //}
             return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

}
