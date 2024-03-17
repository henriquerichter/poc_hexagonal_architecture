package com.test.rest.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.test.system.GetSystemInfoUseCase;

@SpringBootTest
public class SystemInfoControllerTest {

  @InjectMocks
  private SystemInfoController systemInfoController;
  @Mock
  private GetSystemInfoUseCase getSystemInfoUseCase;

  @BeforeEach
  void setUp() {
    when(this.getSystemInfoUseCase.execute(any(GetSystemInfoUseCase.In.class)))
        .thenReturn(new GetSystemInfoUseCase.Out(4, 1000, 500, 500, 2000));
  }

  @Test
  void whenGetSystemInfo_thenSystemInfoIsReturned() {
    // when
    ResponseEntity<GetSystemInfoUseCase.Out> actualSystemInfo = this.systemInfoController.systemInfo();

    // then
    assertNotNull(actualSystemInfo);
    assertEquals(4, actualSystemInfo.getBody().availableProcessors());
    assertEquals(1000, actualSystemInfo.getBody().totalMemory());
    assertEquals(500, actualSystemInfo.getBody().freeMemory());
    assertEquals(500, actualSystemInfo.getBody().allocatedMemory());
    assertEquals(2000, actualSystemInfo.getBody().maxMemory());
  }
}
