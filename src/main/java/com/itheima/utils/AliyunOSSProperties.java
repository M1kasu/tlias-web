package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//定义实体类AliyunOSSProperties ，并交给IOC容器管理
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}