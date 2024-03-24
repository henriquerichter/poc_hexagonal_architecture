package com.test.ports.system;

import com.test.domain.system.SystemInfo;
import org.springframework.stereotype.Component;

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
