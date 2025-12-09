package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@RequiredArgsConstructor
public class SNService implements INotificationService {

    private final SnsClient snsClient;

    @Value("${sns.topic.arn}")
    private String topicArn;

    public void sendMessage(String subject, String body){
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn((topicArn))
                .message(body)
                .subject(subject)
                .build();
        snsClient.publish(publishRequest);
    }

}
