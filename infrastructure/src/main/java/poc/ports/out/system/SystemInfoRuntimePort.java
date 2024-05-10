package poc.ports.out.system;

import org.springframework.stereotype.Component;
import poc.domain.system.SystemInfo;

@Component
public class SystemInfoRuntimePort implements SystemInfoRuntime {

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
