package com.fipe.api1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.infrastructure.cache.VehicleCacheService;

@Service
public class VehicleService {

    private final VehicleCacheService vehicleCacheService;

    public VehicleService(VehicleCacheService vehicleCacheService) {
        this.vehicleCacheService = vehicleCacheService;
    }

    public List<String> getBrands() {
        return vehicleCacheService.getBrands();
    }

    public List<Vehicle> getByBrand(String brand) {
        return vehicleCacheService.getVehiclesByBrand(brand);
    }

    public List<Vehicle> updateVehicle(Vehicle vehicle) {
        return vehicleCacheService.updateVehiclesByBrandCache(vehicle);
    }
    
}
