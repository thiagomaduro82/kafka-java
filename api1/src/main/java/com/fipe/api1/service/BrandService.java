package com.fipe.api1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.Brand;
import com.fipe.api1.infrastructure.cache.BrandCacheService;

@Service
public class BrandService {

    private final BrandCacheService brandCacheService;

    public BrandService(BrandCacheService brandCacheService) {
        this.brandCacheService = brandCacheService;
    }

    public List<Brand> getBrands() {
        return brandCacheService.getVehicles();
    }
    
}
