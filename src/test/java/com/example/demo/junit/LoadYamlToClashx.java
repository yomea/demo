//package com.example.demo.junit;
//
//import org.brotli.dec.BrotliInputStream;
//import org.json.JSONObject;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Random;
//import java.util.zip.GZIPInputStream;
//
//public class LoadYamlToClashx {
//    public static void main(String[] args) throws Exception {
//        while (true) {
//            String auth = registry();
//            String url = getUrl(auth);
//            doFile(url);
//            System.out.println("done");
//            Thread.sleep(1000*60*45);
//        }
//    }
//
//    private static void doFile(String url) {
//        // Clash URL
//        String clashUrl = "clash://install-config?url="+url;
//
//        // 解析出真正的URL
//        String realUrl = extractRealUrl(clashUrl);
//        if (realUrl == null) {
//            System.out.println("Failed to extract URL.");
//            return;
//        }
//
//        printYamlFile(realUrl);
//    }
//
//    private static String getUrl(String auth) throws Exception {
//        // 请求地址
//        String urlString = "https://m1.m2cloud.top/api/v1/user/getSubscribe?t=1723521439840";
//        URL url = new URL(urlString);
//
//        // 打开连接
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        // 添加请求头
//        connection.setRequestProperty("accept", "application/json, text/plain, */*");
//        connection.setRequestProperty("accept-encoding", "gzip, deflate, br, zstd");
//        connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
//        connection.setRequestProperty("authorization", auth);
//        connection.setRequestProperty("content-language", "zh-CN");
//        connection.setRequestProperty("cookie", "crisp-client%2Fsession%2F25858393-ae8e-4202-8fff-39873e838d89=session_f5bbcb1b-18bd-469c-a59b-9ff246fe3eb5");
//        connection.setRequestProperty("priority", "u=1, i");
//        connection.setRequestProperty("referer", "https://m1.m2cloud.top/");
//        connection.setRequestProperty("sec-ch-ua", "\"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"");
//        connection.setRequestProperty("sec-ch-ua-mobile", "?0");
//        connection.setRequestProperty("sec-ch-ua-platform", "\"macOS\"");
//        connection.setRequestProperty("sec-fetch-dest", "empty");
//        connection.setRequestProperty("sec-fetch-mode", "cors");
//        connection.setRequestProperty("sec-fetch-site", "same-origin");
//        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36");
//
//        // 获取响应码
//        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);
//
//        // 获取内容编码
//        String contentEncoding = connection.getHeaderField("Content-Encoding");
//
//        // 读取响应内容
//        InputStream inputStream = connection.getInputStream();
//
//        if ("gzip".equalsIgnoreCase(contentEncoding)) {
//            inputStream = new GZIPInputStream(inputStream);
//        } else if ("br".equalsIgnoreCase(contentEncoding)) {
//            inputStream = new BrotliInputStream(inputStream);
//        }
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//        String inputLine;
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        JSONObject jsonResponse = new JSONObject(response.toString());
//        String result = jsonResponse.getJSONObject("data").getString("subscribe_url");
//
//        System.out.println("url: " + result);
//        return result;
//    }
//
//    private static String getEMail(){
//        Random random = new Random();
//        StringBuilder sb = new StringBuilder();
//
//        // 生成12位数字
//        for (int i = 0; i < 12; i++) {
//            sb.append(random.nextInt(10));
//        }
//
//        // 生成QQ邮箱
//        String qqEmail = sb.toString() + "@qq.com";
//        return qqEmail;
//    }
//
//    private static String registry() throws Exception {
//        // 请求地址
//        URL url = new URL("https://m1.m2cloud.top/api/v1/passport/auth/register");
//
//        // 打开连接
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setDoOutput(true);
//
//        // 设置表单数据
//        String urlParameters = "email="+getEMail()+"&password=@123DSsdfas4";
//
//        // 发送请求
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = urlParameters.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//
//        // 获取响应码
//        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);
//
//        BufferedReader reader;
//        if (responseCode >= 200 && responseCode < 300) {
//            // 处理成功响应
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//        } else {
//            // 处理错误响应
//            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
//        }
//
//        StringBuilder response = new StringBuilder();
//        String responseLine;
//        while ((responseLine = reader.readLine()) != null) {
//            response.append(responseLine.trim());
//        }
//
//        // 输出响应内容
//        System.out.println("Response: " + response.toString());
//        // 解析JSON并获取token值
//        JSONObject jsonResponse = new JSONObject(response.toString());
//        String auth = jsonResponse.getJSONObject("data").getString("auth_data");
//
//        // 输出token值
//        System.out.println("auth: " + auth);
//
//        return auth;
//    }
//
//
//    // 提取真正的URL
//    private static String extractRealUrl(String clashUrl) {
//        try {
//            int urlIndex = clashUrl.indexOf("url=");
//            if (urlIndex == -1) {
//                return null;
//            }
//
//            String urlPart = clashUrl.substring(urlIndex + 4);
//            String[] parts = urlPart.split("&", 2);
//            return URLDecoder.decode(parts[0], "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static void printYamlFile(String url) {
//        try {
//            // 创建URL对象
//            URL yamlUrl = new URL(url);
//
//            // 打开HTTP连接
//            HttpURLConnection connection = (HttpURLConnection) yamlUrl.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            // 读取响应
//            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
//
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            StringBuilder data = new StringBuilder();
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                data.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
//            }
//            inputStream.close();
//            connection.disconnect();
//
//            String url2 = transformUrl(url);
//            System.out.println(url2);
//
//            // 第二个请求
//            try {
//                URL fileUrl = new URL(url2);
//                HttpURLConnection connection2 = (HttpURLConnection) fileUrl.openConnection();
//                connection2.setRequestMethod("GET");
//                connection2.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//                // Use InputStreamReader with UTF-8 to ensure proper encoding
//                InputStream inputStream2 = new BufferedInputStream(connection2.getInputStream());
//                InputStreamReader reader = new InputStreamReader(inputStream2, StandardCharsets.UTF_8);
//                BufferedReader bufferedReader = new BufferedReader(reader);
//
//                StringBuilder data2 = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    data2.append(line).append(System.lineSeparator());
//                }
//
//                bufferedReader.close();
//                connection2.disconnect();
//
//                // Write to file using UTF-8 encoding
//                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/sucf/.config/clash/12.yaml"), StandardCharsets.UTF_8))) {
//                    writer.write(data2.toString());
//                }
//
//                System.out.println("YAML file created successfully!");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String transformUrl(String originalUrl) throws UnsupportedEncodingException {
//        // Base URL
//        String baseUrl = "https://url.v1.mk/sub?target=clash&";
//
//        // Parameters for the transformed URL
//        String encodedUrl = "url=" + URLEncoder.encode(originalUrl, "UTF-8");
//        String additionalParams = "&insert=false"
//                + "&config=" + URLEncoder.encode("https://raw.githubusercontent.com/ACL4SSR/ACL4SSR/master/Clash/config/ACL4SSR_Online_Full_NoAuto.ini", "UTF-8")
//                + "&emoji=true"
//                + "&list=false"
//                + "&xudp=false"
//                + "&udp=false"
//                + "&tfo=false"
//                + "&expand=true"
//                + "&scv=false"
//                + "&fdn=false"
//                + "&new_name=true";
//
//        // Construct the final URL
//        return baseUrl + encodedUrl + additionalParams;
//    }
//}