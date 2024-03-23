package com.test.services.system;

import com.test.aws.s3.S3ClientWrapper;
import com.test.domain.system.SystemInfoStorage;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SystemInfoStorageService implements SystemInfoStorage {

    private final S3ClientWrapper s3ClientWrapper;

    public SystemInfoStorageService(S3ClientWrapper s3ClientWrapper) {
        this.s3ClientWrapper = s3ClientWrapper;
    }

    @Override
    public String save(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory) {
        String content = """
                {
                  "cpuCount": %d,
                  "totalMemory": %d,
                  "freeMemory": %d,
                  "allocatedMemory": %d,
                  "maxMemory": %d
                }
                """.formatted(cpuCount, totalMemory, freeMemory, allocatedMemory, maxMemory);

        String bucketName = "composter";
        String key = "system-info.txt";

        this.s3ClientWrapper.putObject(bucketName, key, content);

        return bucketName + "/" + key;
    }

    @Override
    public File get(String bucketName, String key) {
        return this.s3ClientWrapper.getObject(bucketName, key);
    }
}
