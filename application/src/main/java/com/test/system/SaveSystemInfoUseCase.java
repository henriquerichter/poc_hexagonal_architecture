package com.test.system;

import com.test.UseCase;
import com.test.domain.system.SystemInfoStorage;
import org.springframework.stereotype.Component;

@Component
public class SaveSystemInfoUseCase extends UseCase<SaveSystemInfoUseCase.In, SaveSystemInfoUseCase.Out> {

    private final SystemInfoStorage systemInfoStorage;

    public SaveSystemInfoUseCase(SystemInfoStorage systemInfoStorage) {
        this.systemInfoStorage = systemInfoStorage;
    }

    @Override
    public Out execute(In input) {
        String fileLocation = this.systemInfoStorage.save(
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
