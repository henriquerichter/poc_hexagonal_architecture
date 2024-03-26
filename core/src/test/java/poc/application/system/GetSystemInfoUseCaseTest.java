package poc.application.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poc.domain.system.SystemInfo;
import poc.ports.system.SystemInfoRuntime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetSystemInfoUseCaseTest {

    private GetSystemInfoUseCase getSystemInfoUseCase;
    private SystemInfoRuntime systemInfoRuntime;

    @BeforeEach
    void setUp() {
        this.systemInfoRuntime = mock(SystemInfoRuntime.class);
        when(this.systemInfoRuntime.getSystemInfo())
                .thenReturn(new SystemInfo(4, 100000000, 500000000, 600000000, 700000000));
        this.getSystemInfoUseCase = new GetSystemInfoUseCase(this.systemInfoRuntime);
    }

    @Test
    void givenInput_whenExecute_thenOutput() {
        // given
        GetSystemInfoUseCase.In in = new GetSystemInfoUseCase.In();

        // when
        GetSystemInfoUseCase.Out actualOut = this.getSystemInfoUseCase.execute(in);

        // then
        assertEquals(4, actualOut.cpuCount());
        assertEquals(95, actualOut.totalMemory());
        assertEquals(476, actualOut.freeMemory());
        assertEquals(572, actualOut.allocatedMemory());
        assertEquals(667, actualOut.maxMemory());
    }
}
