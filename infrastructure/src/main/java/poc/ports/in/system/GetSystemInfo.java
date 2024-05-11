package poc.ports.in.system;

import org.springframework.stereotype.Component;
import poc.application.system.GetSystemInfoUseCase;

@Component
public class GetSystemInfo {

    GetSystemInfoUseCase getSystemInfoUseCase;

    public GetSystemInfo(GetSystemInfoUseCase getSystemInfoUseCase) {
        this.getSystemInfoUseCase = getSystemInfoUseCase;
    }

    public String getSystemInfo() {
        GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

        GetSystemInfoUseCase.Out out = getSystemInfoUseCase.execute(in);

        return out.toString();
    }
}
