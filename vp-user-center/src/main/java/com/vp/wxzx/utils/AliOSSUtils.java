package com.vp.wxzx.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */

@Component
public class AliOSSUtils {

    @Autowired
    AliOSSProperties aliOSSProperties;
    /**
     * 实现上传图片到 OSS
     */
    public String upload(MultipartFile file) throws IOException{

        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        // 获取上传文件输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().concat(Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".")));

        // 上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        // 文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭 ossClient
        ossClient.shutdown();

        // 上传 oss 的路径返回
        return url;
    }
}

