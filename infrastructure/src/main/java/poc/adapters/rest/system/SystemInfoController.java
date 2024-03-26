package poc.adapters.rest.system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.application.system.GetSystemInfoUseCase;
import poc.application.system.SaveSystemInfoUseCase;

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

        SaveSystemInfoUseCase.Out fileOut = this.saveSystemInfoUseCase.execute(new SaveSystemInfoUseCase.In(
                out.cpuCount(),
                out.totalMemory(),
                out.freeMemory(),
                out.allocatedMemory(),
                out.maxMemory()));

        System.out.println("fileLocation: " + fileOut.fileLocation());

        return ResponseEntity.ok().body(out);
    }
}
