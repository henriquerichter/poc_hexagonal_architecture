package com.test.rest.system;

import com.test.system.GetSystemInfoUseCase;
import com.test.system.SaveSystemInfoUseCase;
import com.test.system.SaveSystemInfoUseCase.Out;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemInfoController {

    private final GetSystemInfoUseCase getSystemInfoUseCase;
    private final SaveSystemInfoUseCase saveSystemInfoUseCase;

    public SystemInfoController(GetSystemInfoUseCase getSystemInfoUseCase, SaveSystemInfoUseCase saveSystemInfoUseCase) {
        this.getSystemInfoUseCase = getSystemInfoUseCase;
        this.saveSystemInfoUseCase = saveSystemInfoUseCase;
    }

    @GetMapping("/system-info")
    public ResponseEntity<GetSystemInfoUseCase.Out> systemInfo() {
        GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

        GetSystemInfoUseCase.Out out = this.getSystemInfoUseCase.execute(in);

        Out fileOut = this.saveSystemInfoUseCase.execute(new SaveSystemInfoUseCase.In(
                out.availableProcessors(),
                out.totalMemory(),
                out.freeMemory(),
                out.allocatedMemory(),
                out.maxMemory()));

        System.out.println("fileLocation: " + fileOut.fileLocation());

        return ResponseEntity.ok().body(out);
    }
}
