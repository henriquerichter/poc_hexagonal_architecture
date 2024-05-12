package poc.adapters.rest.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import poc.ports.in.system.SystemInfoControllerIn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SystemInfoControllerTest {

    @InjectMocks
    private SystemInfoController systemInfoController;
    @Mock
    private SystemInfoControllerIn systemInfoControllerIn;

    @BeforeEach
    void setUp() {
        when(this.systemInfoControllerIn.getSystemInfo()).thenReturn("{cpuCount=24, totalMemory=84, freeMemory=39, allocatedMemory=44, maxMemory=4076}");
    }

    @Test
    void whenGetSystemInfo_thenSystemInfoIsReturned() {
        // when
        ResponseEntity<String> actualSystemInfo = this.systemInfoController.systemInfo();

        // then
        assertNotNull(actualSystemInfo);
        assertEquals("{cpuCount=24, totalMemory=84, freeMemory=39, allocatedMemory=44, maxMemory=4076}", actualSystemInfo.getBody());
    }
}
