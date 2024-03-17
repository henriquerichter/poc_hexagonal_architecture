package com.test.rest.system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.system.GetSystemInfoUseCase;

@RestController
public class SystemInfoController {

  private final GetSystemInfoUseCase getSystemInfoUseCase;

  public SystemInfoController(GetSystemInfoUseCase getSystemInfoUseCase) {
    this.getSystemInfoUseCase = getSystemInfoUseCase;
  }

  @GetMapping("/system-info")
  public ResponseEntity<GetSystemInfoUseCase.Out> systemInfo() {
    GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

    GetSystemInfoUseCase.Out out = this.getSystemInfoUseCase.execute(in);

    return ResponseEntity.ok().body(out);
  }
}
