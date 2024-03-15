package com.project.contestapplication.storage.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.project.contestapplication.storage.exception.FileDeleteException;
import com.project.contestapplication.storage.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public boolean upload(FileRequest fileRequest) {

        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(fileRequest.getMultipartFile().getInputStream().available());
            PutObjectResult result =amazonS3.putObject(bucket, fileRequest.getFileName() , fileRequest.getMultipartFile().getInputStream(), objMeta);
            log.info("uploaded file: url={}", amazonS3.getUrl(bucket, fileRequest.getFileName()));
            log.info("{}, {}", fileRequest.getMultipartFile().getName(), fileRequest.getMultipartFile().getOriginalFilename() );
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
