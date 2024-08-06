package com.butler.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSnsConfig {

    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessKeyId;

    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;

    @Value("${AWS_REGION}")
    private String region;

    @Bean
    public AmazonSNS amazonSNS() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);
        return AmazonSNSClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2) // seoul
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
