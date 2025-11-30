package com.fipe.api1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.domain.repository.VehicleRepository;
import com.fipe.api1.infrastructure.cache.VehicleCacheService;

@Service
public class VehicleService {

    private final VehicleCacheService vehicleCacheService;
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleCacheService vehicleCacheService, VehicleRepository vehicleRepository) {
        this.vehicleCacheService = vehicleCacheService;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleCacheService.getVehicles();
    }

    public List<Vehicle> getByBrand(Long brandId) {
        return vehicleCacheService.getVehiclesByBrand(brandId);
    }

    public Vehicle update(Long vehicleId, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (existingVehicle != null) {
            existingVehicle.setModelName(vehicle.getModelName());
            existingVehicle.setObservation(vehicle.getObservation());
            return vehicleRepository.save(existingVehicle);
        }
        return null;
    }
    
}
