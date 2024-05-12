package poc.application.system;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.system.ISystemInfoRuntimeOut;

@Component
public class GetSystemInfoUseCase extends UseCase<GetSystemInfoUseCase.In, GetSystemInfoUseCase.Out> {

    private final ISystemInfoRuntimeOut systemInfoRuntime;

    public GetSystemInfoUseCase(ISystemInfoRuntimeOut systemInfoRuntime) {
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
    }
}
