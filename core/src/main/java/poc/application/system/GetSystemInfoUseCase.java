package poc.application.system;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.system.SystemInfoRuntime;

@Component
public class GetSystemInfoUseCase extends UseCase<GetSystemInfoUseCase.In, GetSystemInfoUseCase.Out> {

    private final SystemInfoRuntime systemInfoRuntime;

    public GetSystemInfoUseCase(SystemInfoRuntime systemInfoRuntime) {
        this.systemInfoRuntime = systemInfoRuntime;
    }

    @Override
    public Out execute(In input) {
        return new Out(
                systemInfoRuntime.getSystemInfo().cpuCount(),
                systemInfoRuntime.getSystemInfo().totalMemory(),
                systemInfoRuntime.getSystemInfo().freeMemory(),
                systemInfoRuntime.getSystemInfo().allocatedMemory(),
                systemInfoRuntime.getSystemInfo().maxMemory());
    }

    public record In() {
    }

    public record Out(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory,
                      long maxMemory) {

        @Override
        public String toString() {
            return """
                    {
                        "cpuCount": %d,
                        "totalMemory": %d,
                        "freeMemory": %d,
                        "allocatedMemory": %d,
                        "maxMemory": %d
                    }
                    """.formatted(cpuCount, totalMemory, freeMemory, allocatedMemory, maxMemory);
        }
    }
}
