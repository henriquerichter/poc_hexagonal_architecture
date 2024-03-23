package com.test.domain.game;

import java.io.File;

public interface GameStorage {

  void save(String bucketName, String key, String content);

  File get(String bucketName, String key);
}
