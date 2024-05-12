package poc.ports.out.game;

import org.springframework.stereotype.Component;
import poc.adapters.aws.s3.S3ClientWrapper;

import java.io.File;
import java.util.Objects;

@Component
public class GameStorageOut implements IGameStorageOut {

    private final S3ClientWrapper s3ClientWrapper;

    public GameStorageOut(S3ClientWrapper s3ClientWrapper) {
        this.s3ClientWrapper = s3ClientWrapper;
    }

    @Override
    public void save(String bucketName, String key, String content) {
        Objects.requireNonNull(bucketName, "bucketName must not be null");
        Objects.requireNonNull(key, "key must not be null");
        Objects.requireNonNull(content, "content must not be null");
        this.s3ClientWrapper.putObject(bucketName, key, content);
    }

    @Override
    public File get(String bucketName, String key) {
        Objects.requireNonNull(bucketName, "bucketName must not be null");
        Objects.requireNonNull(key, "key must not be null");
        return this.s3ClientWrapper.getObject(bucketName, key);
    }
}
