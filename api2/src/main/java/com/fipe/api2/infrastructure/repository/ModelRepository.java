package com.fipe.api2.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fipe.api2.domain.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findByBrandId(Integer brandId);
    
}
