package com.awsproyect.AWS_Proyect.Models;

import jakarta.persistence.Entity;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class AlumnoSesion {
    private String id;
    private Instant fecha;
    private Long alumnoId;
    private boolean activo;
    private String sessionString;

    @DynamoDbPartitionKey
    public String getId(){return id;}
    @DynamoDbSecondaryPartitionKey(indexNames = "session-String-indice")
    public String getSessionString() {
        return sessionString;
    }

    public void setSessionString(String sessionString) {
        this.sessionString = sessionString;
    }


}
