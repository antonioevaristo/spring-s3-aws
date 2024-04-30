package com.antonioevaristo.awsessential.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Value("${aws.access.id}")
    private String accessKey;
    @Value("${aws.access.secret}")
    private String accessSecret;
    @Value("${aws.s3.region}")
    private String bucketRegion;

    @Bean
    public AmazonS3 amazonS3(){
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(bucketRegion))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
