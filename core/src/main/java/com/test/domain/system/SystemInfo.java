package com.test.domain.system;

public record SystemInfo(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory) {

    public SystemInfo(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory) {
        this.cpuCount = cpuCount;
        this.totalMemory = bytesToMegabytes(totalMemory);
        this.freeMemory = bytesToMegabytes(freeMemory);
        this.allocatedMemory = bytesToMegabytes(allocatedMemory);
        this.maxMemory = bytesToMegabytes(maxMemory);
    }

    private long bytesToMegabytes(long bytes) {
        return bytes / (1024 * 1024);
    }
}
