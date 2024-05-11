package poc.ports.in.system;

import org.springframework.stereotype.Component;
import poc.application.system.GetSystemInfoUseCase;
import poc.application.system.SaveSystemInfoUseCase;

@Component
public class SaveSystemInfo {

    private final GetSystemInfoUseCase getSystemInfoUseCase;
    private final SaveSystemInfoUseCase saveSystemInfoUseCase;

    public SaveSystemInfo(GetSystemInfoUseCase getSystemInfoUseCase, SaveSystemInfoUseCase saveSystemInfoUseCase) {
        this.getSystemInfoUseCase = getSystemInfoUseCase;
        this.saveSystemInfoUseCase = saveSystemInfoUseCase;
    }

    public String saveSystemInfo() {
        GetSystemInfoUseCase.In getSystemIn = new GetSystemInfoUseCase.In();

        GetSystemInfoUseCase.Out getSystemOut = getSystemInfoUseCase.execute(getSystemIn);

        SaveSystemInfoUseCase.In saveSystemIn = new SaveSystemInfoUseCase.In(getSystemOut.cpuCount(),
                getSystemOut.totalMemory(),
                getSystemOut.freeMemory(),
                getSystemOut.allocatedMemory(),
                getSystemOut.maxMemory());

        SaveSystemInfoUseCase.Out saveSystemOut = saveSystemInfoUseCase.execute(saveSystemIn);

        return saveSystemOut.fileLocation();
    }
}
