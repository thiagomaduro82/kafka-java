package com.fipe.api1.infrastructure.cache;

import java.util.List;

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

    @Cacheable(cacheNames = "vehicles")
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Cacheable(cacheNames = "vehiclesByBrands", key = "#brand")
    public List<Vehicle> getVehiclesByBrand(Long brandId) {
        return vehicleRepository.findByBrandId(brandId);
    }

}
