package com.fipe.api1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.domain.model.VehicleDTO;
import com.fipe.api1.service.VehicleService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class VehicleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------------------------------------------------------
    // GET /vehicles
    // ---------------------------------------------------------------
    @Test
    void getVehicles_ShouldReturnListOfVehicles() throws Exception {
        Vehicle v1 = new Vehicle();
        v1.setId(1L);
        v1.setModelName("Carro A");

        Vehicle v2 = new Vehicle();
        v2.setId(2L);
        v2.setModelName("Carro B");

        List<Vehicle> vehicles = Arrays.asList(v1, v2);

        Mockito.when(vehicleService.getVehicles()).thenReturn(vehicles);

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].modelName").value("Carro A"))
                .andExpect(jsonPath("$[1].modelName").value("Carro B"));
    }

    // ---------------------------------------------------------------
    // GET /vehicles/by-brand/{brandId}
    // ---------------------------------------------------------------
    @Test
    void getByBrand_ShouldReturnVehiclesFromBrand() throws Exception {
        Vehicle v1 = new Vehicle();
        v1.setId(10L);
        v1.setModelName("Modelo X");

        List<Vehicle> vehicles = Arrays.asList(v1);

        Mockito.when(vehicleService.getByBrand(5L)).thenReturn(vehicles);

        mockMvc.perform(get("/vehicles/by-brand/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].modelName").value("Modelo X"));
    }

    // ---------------------------------------------------------------
    // PUT /vehicles/update/{vehicleId}
    // ---------------------------------------------------------------
    @Test
    void updateVehicle_ShouldReturnUpdatedVehicle() throws Exception {
        VehicleDTO dto = new VehicleDTO();
        dto.setModelName("Nome Atualizado");
        dto.setObservation("Obs Atualizada");

        Vehicle updated = new Vehicle();
        updated.setId(7L);
        updated.setModelName("Nome Atualizado");
        updated.setObservation("Obs Atualizada");

        Mockito.when(vehicleService.update(Mockito.eq(7L), Mockito.any(Vehicle.class)))
                .thenReturn(updated);

        mockMvc.perform(
                put("/vehicles/update/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.modelName").value("Nome Atualizado"))
                .andExpect(jsonPath("$.observation").value("Obs Atualizada"));
    }

    // ---------------------------------------------------------------
    // PUT not found
    // ---------------------------------------------------------------
    @Test
    void updateVehicle_ShouldReturnNotFound_WhenVehicleDoesNotExist() throws Exception {
        VehicleDTO dto = new VehicleDTO();
        dto.setModelName("Teste");

        Mockito.when(vehicleService.update(Mockito.eq(999L), Mockito.any(Vehicle.class)))
                .thenReturn(null);

        mockMvc.perform(
                put("/vehicles/update/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
    
}
