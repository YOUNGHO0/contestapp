package com.project.contestapplication.infrastructure.filestorage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.project.contestapplication.exception.FileDeleteException;
import com.project.contestapplication.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class OciFileStorage implements FileStorage {
    // OCI Storage supports aws s3 upload method
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    @Override
    public boolean upload(File file) {

        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(file.getMultipartFile().getInputStream().available());
            PutObjectResult result =amazonS3.putObject(bucket,file.getFileName() ,file.getMultipartFile().getInputStream(), objMeta);
            log.info("uploaded file: url={}", amazonS3.getUrl(bucket, file.getFileName()));
            log.info("{}, {}",file.getMultipartFile().getName(), file.getMultipartFile().getOriginalFilename() );
        } catch (IOException e) {
            throw new FileUploadException();
        }

        return true;
    }

    @Override
    public boolean delete(String key) {
        try {
            amazonS3.deleteObject(bucket,key);
        } catch (Exception e){
            log.info("{}dd",e.getMessage());
            throw new FileDeleteException();
        }

        return false;
    }
}
