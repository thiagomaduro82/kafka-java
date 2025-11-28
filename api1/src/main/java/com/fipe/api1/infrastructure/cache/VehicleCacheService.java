package com.fipe.api1.infrastructure.cache;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.Vehicle;
import com.fipe.api1.domain.repository.VehicleRepository;

@Service
public class VehicleCacheService {

    private final VehicleRepository vehicleRepository;

    public VehicleCacheService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Cacheable(cacheNames = "brands")
    public List<String> getBrands() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> vehicle.getBrand())
                .distinct()
                .toList();
    }

    @Cacheable(cacheNames = "vehiclesByBrands", key = "#brand")
    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    @CachePut(cacheNames = "models", key = "#vehicle.brand")
    public List<Vehicle> updateVehiclesByBrandCache(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return vehicleRepository.findByBrand(vehicle.getBrand());
    }
    
}
