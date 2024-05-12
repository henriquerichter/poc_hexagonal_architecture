package poc.ports.in.system;

import org.springframework.stereotype.Component;
import poc.adapters.json.To;
import poc.application.system.GetSystemInfoUseCase;
import poc.application.system.SaveSystemInfoUseCase;

@Component
public class SystemInfoControllerIn {

    private final GetSystemInfoUseCase getSystemInfoUseCase;
    private final SaveSystemInfoUseCase saveSystemInfoUseCase;

    public SystemInfoControllerIn(GetSystemInfoUseCase getSystemInfoUseCase, SaveSystemInfoUseCase saveSystemInfoUseCase) {
        this.getSystemInfoUseCase = getSystemInfoUseCase;
        this.saveSystemInfoUseCase = saveSystemInfoUseCase;
    }

    public String getSystemInfo() {
        GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

        GetSystemInfoUseCase.Out out = getSystemInfoUseCase.execute(in);

        return To.json(out);
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

        return To.json(saveSystemOut.fileLocation());
    }
}
