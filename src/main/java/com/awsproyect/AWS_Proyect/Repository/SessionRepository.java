package com.awsproyect.AWS_Proyect.Repository;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.AlumnoSesion;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepository {
    private final DynamoDbTable<AlumnoSesion> sessionTable;

    public SessionRepository(DynamoDbEnhancedClient enhancedClient){
        this.sessionTable = enhancedClient.table("sessionTable", TableSchema.fromClass(AlumnoSesion.class));
    }

    public AlumnoSesion save(AlumnoSesion alumnoSesion){
        if(alumnoSesion.getId()==null){
            alumnoSesion.setId(UUID.randomUUID().toString());
        }
        sessionTable.putItem(alumnoSesion);
        return alumnoSesion;
    }

    public Optional<AlumnoSesion> findSessionString(String sessionString){
        Key key = Key.builder()
                .partitionValue(sessionString)
                .build();

        QueryConditional query = QueryConditional.keyEqualTo(key);

        QueryEnhancedRequest queryRequest = QueryEnhancedRequest.builder()
                .queryConditional(query)
                .limit(1)
                .build();
        var index = sessionTable.index("session-String-indice");

        return index.query(query)
                .stream()
                .flatMap(page -> page.items().stream())
                .findFirst();
    }
}
