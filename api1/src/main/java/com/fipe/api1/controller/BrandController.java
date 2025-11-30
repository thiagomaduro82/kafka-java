package com.fipe.api1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api1.domain.model.Brand;
import com.fipe.api1.service.BrandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brands", description = "Endpoints para gerenciamento de marcas de veículos")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @Operation(summary = "Retorna todas as marcas")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }
    
}
