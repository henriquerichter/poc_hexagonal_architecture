package com.test.infrastructure.services.system;

import org.springframework.stereotype.Component;

import com.test.application.domain.system.SystemInfo;
import com.test.application.services.system.SystemInfoService;

@Component
public class SystemInfoRuntimeService implements SystemInfoService {

  @Override
  public SystemInfo getSystemInfo() {
    SystemInfo systemInfo = new SystemInfo(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().totalMemory(),
        Runtime.getRuntime().freeMemory(),
        Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
        Runtime.getRuntime().maxMemory());
    return systemInfo;
  }
}
