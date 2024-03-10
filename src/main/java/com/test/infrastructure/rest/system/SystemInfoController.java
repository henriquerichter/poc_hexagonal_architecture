package com.test.infrastructure.rest.system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.application.usecases.system.GetSystemInfoUseCase;

@RestController
public class SystemInfoController {

  private final GetSystemInfoUseCase getSystemInfoUseCase;

  public SystemInfoController(GetSystemInfoUseCase getSystemInfoUseCase) {
    this.getSystemInfoUseCase = getSystemInfoUseCase;
  }

  @GetMapping("/system-info")
  public ResponseEntity<?> systemInfo() {
    return ResponseEntity.ok().body(getSystemInfoUseCase.execute(new GetSystemInfoUseCase.In()));
  }
}
