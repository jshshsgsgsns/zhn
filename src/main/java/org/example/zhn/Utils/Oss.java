package org.example.zhn.Utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Oss {

    //    文件存储目录
    private static final String filePath = "zhn/";

    private static final String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    private static final String bucketName = "2217795051";
    // 替换为您的 Bucket 区域
    private static final String region = "cn-chengdu";
    // 创建 OSSClient 实例
    private static final EnvironmentVariableCredentialsProvider credentialsProvider;
    static {
        try {
            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    private static final OSS ossClient = OSSClientBuilder.create()
            .endpoint(endpoint)
            .credentialsProvider(credentialsProvider)
            .region(region)
            .build();



    //          上传图像
    public static boolean upImageFile (String objectName, MultipartFile multipartFile) {
        // 判断文件是否为空
        if (multipartFile == null || multipartFile.isEmpty()) {
            log.error("上传的文件为空");
            return false;
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 创建 PutObjectRequest 上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,filePath + objectName, inputStream);
            // 上传文件到 OSS
            ossClient.putObject(putObjectRequest);
            log.info("{} 上传成功", objectName);
            return true;
        } catch (IOException e) {
            log.error("文件上传失败: {}", e.getMessage());
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());
            log.error(e.getCause().toString());
            return false;
        }
    }

    public static boolean upFile (File file) {
        if (file == null ) {
            log.error("上传的文件为空");
            return false;
        }

        // 创建 PutObjectRequest 上传请求
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,filePath +file.getName(), file);
        // 上传文件到 OSS
        ossClient.putObject(putObjectRequest);
        log.info("{} 上传成功", file.getName());
        return true;
    }

//    删除文件
    public static boolean deleteFile (String objectName) {
        try{
            ossClient.deleteObject(bucketName,filePath + objectName);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return false;
    }




}


