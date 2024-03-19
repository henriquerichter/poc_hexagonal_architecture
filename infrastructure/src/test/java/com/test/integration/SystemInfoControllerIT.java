package com.test.integration;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = com.test.Main.class)
@AutoConfigureMockMvc
public class SystemInfoControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenGetSystemInfo_thenStatus200() throws Exception {
    mockMvc.perform(get("/system-info"))
        .andExpect(status().isOk())
        .andExpect(content().string(matchesRegex(
            "\\{\"availableProcessors\":\\d+,\"totalMemory\":\\d+,\"freeMemory\":\\d+,\"allocatedMemory\":\\d+,\"maxMemory\":\\d+\\}")));
  }
}
