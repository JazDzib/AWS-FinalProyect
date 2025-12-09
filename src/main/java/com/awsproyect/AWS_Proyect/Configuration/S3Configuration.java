package com.awsproyect.AWS_Proyect.Configuration;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Configuration {
    @Value("${aws.region}")
    private String aswRegion;
    @Value("${aws.acceskeyId}")
    private String awsAccessKey;
    @Value("${aws.secretkey}")
    private String awsSecretKey;
    @Value("${aws.sessionToken}")
    private String awsSessionToken;

    @Bean
    public S3Client s3Client() {
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsSessionToken);

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCreds);

        return S3Client.builder()
                .region(Region.of(aswRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
