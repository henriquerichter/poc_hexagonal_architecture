package poc.application.system;

import org.springframework.stereotype.Component;
import poc.application.UseCase;
import poc.ports.out.system.ISystemInfoStorageOut;

@Component
public class SaveSystemInfoUseCase extends UseCase<SaveSystemInfoUseCase.In, SaveSystemInfoUseCase.Out> {

    private final ISystemInfoStorageOut systemInfoStorageOut;

    public SaveSystemInfoUseCase(ISystemInfoStorageOut systemInfoStorageOut) {
        this.systemInfoStorageOut = systemInfoStorageOut;
    }

    @Override
    public Out execute(In input) {
        String fileLocation = this.systemInfoStorageOut.save(
                input.cpuCount(),
                input.totalMemory(),
                input.freeMemory(),
                input.allocatedMemory(),
                input.maxMemory());

        return new Out(fileLocation);
    }

    public record In(int cpuCount, long totalMemory, long freeMemory, long allocatedMemory, long maxMemory) {
    }

    public record Out(String fileLocation) {
    }
}
