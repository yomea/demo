package com.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wuzhenhong
 * @date 2025/3/7 17:33
 */
public class Tset {

    private String endpoint = "http://192.168.218.175:9001/";
    private String bucket = "test";

    private MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(endpoint)
            .credentials("bBwyHDjmaYVk0NmrnVCm", "vuxzD8O8O6W3xIz51Pg3ZGqZCXeB1DhjZydPxn4S").build();
        return minioClient;
    }


    @Test
    public void listBuckets() throws Exception {

        MinioClient minioClient = this.minioClient();
        List<Bucket> buckets = minioClient.listBuckets();
        System.out.println(buckets);
    }

    @Test
    public void uploadCommand() throws Exception {

        MinioClient minioClient = this.minioClient();
        MultipartFile file = null;
        try (InputStream inputStream = file.getInputStream()) {
            // UUID 避免文件被覆盖
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
            //文件访问 URL
            System.out.println(endpoint + "/test/" + fileName);
        } catch (Exception e) {
            throw new RuntimeException("上传失败");
        }
    }

    public String generatePreSignedUrl(String fileName) {
        try {
            MinioClient minioClient = this.minioClient();
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(bucket)
                    .object(fileName)
                    .expiry(24, TimeUnit.HOURS)  // 有效期 24 小时
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("生成预签名 URL 失败");
        }
    }


}
