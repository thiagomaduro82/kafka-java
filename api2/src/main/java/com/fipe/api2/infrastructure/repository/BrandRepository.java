package com.fipe.api2.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fipe.api2.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
