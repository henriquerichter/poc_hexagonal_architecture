package com.test.services.system;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.system.SystemInfo;

@SpringBootTest
public class SystemInfoRuntimeServiceTest {

  private final SystemInfoRuntimeService systemInfoRuntimeService = new SystemInfoRuntimeService();

  @Test
  void whenGetSystemInfo_thenSystemInfoIsReturned() {
    // when
    SystemInfo actualSystemInfo = this.systemInfoRuntimeService.getSystemInfo();

    // then
    assertNotNull(actualSystemInfo);
    assertNotNull(actualSystemInfo.cpuCount());
    assertNotNull(actualSystemInfo.totalMemory());
    assertNotNull(actualSystemInfo.freeMemory());
    assertNotNull(actualSystemInfo.allocatedMemory());
    assertNotNull(actualSystemInfo.maxMemory());
  }
}
