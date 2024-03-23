package com.test.services.game;

import com.test.aws.s3.S3ClientWrapper;
import com.test.domain.game.GameStorage;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class GameStorageService implements GameStorage {

    private final S3ClientWrapper s3ClientWrapper;

    public GameStorageService(S3ClientWrapper s3ClientWrapper) {
        this.s3ClientWrapper = s3ClientWrapper;
    }

    @Override
    public void save(String bucketName, String key, String content) {
        this.s3ClientWrapper.putObject(bucketName, key, content);
    }

    @Override
    public File get(String bucketName, String key) {
        return this.s3ClientWrapper.getObject(bucketName, key);
    }
}
