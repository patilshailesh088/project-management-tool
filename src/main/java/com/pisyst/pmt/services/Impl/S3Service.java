package com.pisyst.pmt.services.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file) {

        String key = "documents/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentType(file.getContentType());
            metaData.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, key, file.getInputStream(), metaData);

            return amazonS3.getUrl(bucketName, key).toString();

        } catch (IOException e) {
            throw new RuntimeException("Error while uploading File ", e);
        }

    }
}
