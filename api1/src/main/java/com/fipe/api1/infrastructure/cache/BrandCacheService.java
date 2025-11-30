package com.fipe.api1.infrastructure.cache;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.Brand;
import com.fipe.api1.domain.repository.BrandRepository;

@Service
public class BrandCacheService {

    private final BrandRepository brandRepository;

    public BrandCacheService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Cacheable(cacheNames = "brands")
    public List<Brand> getVehicles() {
        return brandRepository.findAll();
    }

}
