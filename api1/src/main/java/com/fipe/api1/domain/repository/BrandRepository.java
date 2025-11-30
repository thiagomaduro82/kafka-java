package com.fipe.api1.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fipe.api1.domain.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
