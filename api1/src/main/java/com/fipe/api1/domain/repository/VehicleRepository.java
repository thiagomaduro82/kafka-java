package com.fipe.api1.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fipe.api1.domain.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    List<Vehicle> findByBrandId(Long brandId);
    
}
