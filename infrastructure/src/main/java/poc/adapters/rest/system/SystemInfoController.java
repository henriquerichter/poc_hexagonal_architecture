package poc.adapters.rest.system;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.ports.in.system.SystemInfoControllerIn;

@RestController
public class SystemInfoController {

    private final SystemInfoControllerIn systemInfoControllerIn;

    public SystemInfoController(SystemInfoControllerIn systemInfoControllerIn) {
        this.systemInfoControllerIn = systemInfoControllerIn;
    }

    @GetMapping(value = "/system-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> systemInfo() {

        String systemInfo = this.systemInfoControllerIn.getSystemInfo();

        System.out.println("System info: " + systemInfo);

        return ResponseEntity.ok().body(systemInfo);
    }

    @GetMapping("/system-info/save")
    public ResponseEntity<String> saveSystemInfo() {
        String systemInfo = this.systemInfoControllerIn.saveSystemInfo();

        System.out.println("System info saved: " + systemInfo);

        return ResponseEntity.ok().body(systemInfo);
    }
}
