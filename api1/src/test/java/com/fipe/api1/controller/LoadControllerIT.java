package com.fipe.api1.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mockito;

import com.fipe.api1.service.InitialLoadService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class LoadControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InitialLoadService initialLoadService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public InitialLoadService initialLoadService() {
            return Mockito.mock(InitialLoadService.class);
        }
    }

    @Test
    void loadInitialData_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(get("/load-initial-data"))
                .andExpect(status().isOk())
                .andExpect(content().string("Initial data load triggered."));

        verify(initialLoadService, times(1)).loadBrands();
    }
    
}
