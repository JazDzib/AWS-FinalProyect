package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Service.IS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements IS3Service {

    private final S3Client s3Client;

    private final String IMAGES = "fotoPerfil/";

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = IMAGES + uuid + ".png";

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return String.format("https://%s.s3.amazonaws.com/%s",
                bucketName,  fileName);
    }
}
