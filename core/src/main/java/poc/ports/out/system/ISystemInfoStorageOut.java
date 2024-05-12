package poc.ports.out.system;

import java.io.File;

public interface ISystemInfoStorageOut {

    String save(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory);

    File get(String bucketName, String key);
}
