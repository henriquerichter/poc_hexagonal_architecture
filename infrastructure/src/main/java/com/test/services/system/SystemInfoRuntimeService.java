package com.test.services.system;

import org.springframework.stereotype.Component;

import com.test.domain.system.SystemInfo;
import com.test.domain.system.SystemInfoService;

@Component
public class SystemInfoRuntimeService implements SystemInfoService {

  @Override
  public SystemInfo getSystemInfo() {
    return new SystemInfo(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().totalMemory(),
        Runtime.getRuntime().freeMemory(),
        Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
        Runtime.getRuntime().maxMemory());
  }
}
