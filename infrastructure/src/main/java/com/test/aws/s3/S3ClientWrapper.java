package com.test.aws.s3;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;

@Component
public class S3ClientWrapper {

    private final S3Client s3Client;

    public S3ClientWrapper(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void putObject(String bucketName, String key, String content) {
        this.s3Client.putObject(c -> c.bucket(bucketName).key(key), RequestBody.fromString(content));
    }

    public File getObject(String bucketName, String key) {
        File file = new File(key);
        this.s3Client.getObject(c -> c.bucket(bucketName).key(key), file.toPath());
        return file;
    }

    public void deleteObject(String bucketName, String key) {
        this.s3Client.deleteObject(c -> c.bucket(bucketName).key(key));
    }

    public void createBucket(String bucketName) {
        this.s3Client.createBucket(c -> c.bucket(bucketName));
    }
}
