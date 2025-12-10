package com.awsproyect.AWS_Proyect.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoConfig {
    @Value("${aws.region}")
    private String aswRegion;
    @Value("${aws.acceskeyId}")
    private String awsAccessKey;
    @Value("${aws.secretkey}")
    private String awsSecretKey;
    @Value("${aws.sessionToken}")
    private String awsSessionToken;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsSessionCredentials awsCredentials = AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsSessionToken);
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);
        return DynamoDbClient.builder()
                .region(Region.of(aswRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}
