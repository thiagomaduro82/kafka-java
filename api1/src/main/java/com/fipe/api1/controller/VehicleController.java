package com.fipe.api1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands() {
        List<String> brands = vehicleService.getBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<List<Vehicle>> getByBrand(@PathVariable String brand) {
        List<Vehicle> vehicles = vehicleService.getByBrand(brand);
        return ResponseEntity.ok(vehicles); 
    }

    @PutMapping("/update")
    public ResponseEntity<List<Vehicle>> updateVehicle(Vehicle vehicle) {
        List<Vehicle> updatedVehicles = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(updatedVehicles);
    }
    
}
