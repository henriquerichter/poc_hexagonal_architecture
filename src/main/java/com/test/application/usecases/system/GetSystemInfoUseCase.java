package com.test.application.usecases.system;

import org.springframework.stereotype.Component;

import com.test.application.services.system.SystemInfoService;
import com.test.application.usecases.UseCase;

@Component
public class GetSystemInfoUseCase extends UseCase<GetSystemInfoUseCase.In, GetSystemInfoUseCase.Out> {

  private final SystemInfoService systemInfoService;

  public GetSystemInfoUseCase(SystemInfoService systemInfoService) {
    this.systemInfoService = systemInfoService;
  }

  @Override
  public Out execute(In input) {
    return new Out(
        systemInfoService.getSystemInfo().cpuCount(),
        systemInfoService.getSystemInfo().totalMemory(),
        systemInfoService.getSystemInfo().freeMemory(),
        systemInfoService.getSystemInfo().allocatedMemory(),
        systemInfoService.getSystemInfo().maxMemory());
  }

  public record In() {
  }

  public record Out(int availableProcessors, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory) {
  }
}
