package com.test.domain.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SystemInfoTest {

  @Test
  void whenInstantiated_thenGettersReturnCorrectValues() {
    // when
    SystemInfo systemInfo = new SystemInfo(4, 40000000, 50000000, 50000000, 200000000);

    // then
    assertEquals(4, systemInfo.cpuCount());
    assertEquals(38, systemInfo.totalMemory());
    assertEquals(47, systemInfo.freeMemory());
    assertEquals(47, systemInfo.allocatedMemory());
    assertEquals(190, systemInfo.maxMemory());
  }
}
