package poc.adapters.rest.system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.ports.in.system.GetSystemInfo;
import poc.ports.in.system.SaveSystemInfo;

@RestController
public class SystemInfoController {

    private final GetSystemInfo getSystemInfo;
    private final SaveSystemInfo saveSystemInfo;

    public SystemInfoController(GetSystemInfo getSystemInfo, SaveSystemInfo saveSystemInfo) {
        this.getSystemInfo = getSystemInfo;
        this.saveSystemInfo = saveSystemInfo;
    }

    @GetMapping("/system-info")
    public ResponseEntity<String> systemInfo() {

        String systemInfo = this.getSystemInfo.getSystemInfo();

        System.out.println("System info: " + systemInfo);

        return ResponseEntity.ok().body(systemInfo);
    }

    @PostMapping("/system-info")
    public ResponseEntity<String> saveSystemInfo() {
        String systemInfo = this.saveSystemInfo.saveSystemInfo();

        System.out.println("System info saved: " + systemInfo);

        return ResponseEntity.ok().body(systemInfo);
    }
}
