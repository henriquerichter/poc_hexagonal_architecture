package poc.ports.out.game;

import java.io.File;

public interface IGameStorageOut {

    void save(String bucketName, String key, String content);

    File get(String bucketName, String key);
}
