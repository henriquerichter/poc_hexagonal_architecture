package com.test.ports.system;

import com.test.domain.system.SystemInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SystemInfoRuntimePortTest {

    private final SystemInfoRuntimePort systemInfoRuntimePort = new SystemInfoRuntimePort();

    @Test
    void whenGetSystemInfo_thenSystemInfoIsReturned() {
        // when
        SystemInfo actualSystemInfo = this.systemInfoRuntimePort.getSystemInfo();

        // then
        assertNotNull(actualSystemInfo);
        assertNotNull(actualSystemInfo.cpuCount());
        assertNotNull(actualSystemInfo.totalMemory());
        assertNotNull(actualSystemInfo.freeMemory());
        assertNotNull(actualSystemInfo.allocatedMemory());
        assertNotNull(actualSystemInfo.maxMemory());
    }
}
