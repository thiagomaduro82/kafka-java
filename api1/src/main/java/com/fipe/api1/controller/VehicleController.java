package com.fipe.api1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.domain.model.VehicleDTO;
import com.fipe.api1.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "Endpoints para gerenciamento de veículos")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os veículos")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/by-brand/{brandId}")
    @Operation(summary = "Retorna veículos pelo ID da marca")
    public ResponseEntity<List<Vehicle>> getByBrand(@PathVariable String brandId) {
        List<Vehicle> vehicles = vehicleService.getByBrand(Long.valueOf(brandId));
        return ResponseEntity.ok(vehicles); 
    }

    @PutMapping("/update/{vehicleId}")
    @Operation(summary = "Atualiza um veículo existente")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModelName(vehicleDTO.getModelName());
        vehicle.setObservation(vehicleDTO.getObservation());
        Vehicle updatedVehicle = vehicleService.update(vehicleId, vehicle);
        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
