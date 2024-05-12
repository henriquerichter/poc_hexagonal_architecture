package poc.ports.out.system;

import org.junit.jupiter.api.Test;
import poc.domain.system.SystemInfo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SystemInfoRuntimeOutTest {

    private final SystemInfoRuntimeOut systemInfoRuntimeOut = new SystemInfoRuntimeOut();

    @Test
    void whenGetSystemInfo_thenSystemInfoIsReturned() {
        // when
        SystemInfo actualSystemInfo = this.systemInfoRuntimeOut.getSystemInfo();

        // then
        assertNotNull(actualSystemInfo);
        assertNotNull(actualSystemInfo.cpuCount());
        assertNotNull(actualSystemInfo.totalMemory());
        assertNotNull(actualSystemInfo.freeMemory());
        assertNotNull(actualSystemInfo.allocatedMemory());
        assertNotNull(actualSystemInfo.maxMemory());
    }
}
