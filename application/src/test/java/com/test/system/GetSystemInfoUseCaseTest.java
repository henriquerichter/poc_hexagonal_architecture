
package com.test.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.domain.system.SystemInfo;
import com.test.domain.system.SystemInfoService;

public class GetSystemInfoUseCaseTest {

  private GetSystemInfoUseCase getSystemInfoUseCase;
  private SystemInfoService systemInfoService;

  @BeforeEach
  void setUp() {
    this.systemInfoService = mock(SystemInfoService.class);
    when(this.systemInfoService.getSystemInfo())
        .thenReturn(new SystemInfo(4, 100000000, 500000000, 600000000, 700000000));
    this.getSystemInfoUseCase = new GetSystemInfoUseCase(this.systemInfoService);
  }

  @Test
  void givenInput_whenExecute_thenOutput() {
    // given
    GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

    // when
    GetSystemInfoUseCase.Out actualOut = this.getSystemInfoUseCase.execute(in);

    // then
    assertEquals(4, actualOut.availableProcessors());
    assertEquals(95, actualOut.totalMemory());
    assertEquals(476, actualOut.freeMemory());
    assertEquals(572, actualOut.allocatedMemory());
    assertEquals(667, actualOut.maxMemory());
  }
}
