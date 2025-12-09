package com.awsproyect.AWS_Proyect.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SNSConfig {
    @Value("${aws.region}")
    private String aswRegion;
    @Value("${aws.acceskeyId}")
    private String awsAccessKey;
    @Value("${aws.secretkey}")
    private String awsSecretKey;
    @Value("${aws.sessionToken}")
    private String awsSessionToken;

    @Bean
    public SnsClient snsClient(){
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsSessionToken);

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCreds);

        return SnsClient.builder()
                .region(Region.of(aswRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }

}
