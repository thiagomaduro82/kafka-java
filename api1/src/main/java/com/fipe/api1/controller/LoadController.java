package com.fipe.api1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api1.service.InitialLoadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Load", description = "Endpoint para carregamento inicial de dados")
public class LoadController {

    private final InitialLoadService initialLoadService;

    public LoadController(InitialLoadService initialLoadService) {
        this.initialLoadService = initialLoadService;
    }

    // Endpoint to trigger the initial load
    @GetMapping("/load-initial-data")
    @Operation(summary = "Carrega os dados iniciais de marcas e modelos de veículos")
    public String loadInitialData() {
        initialLoadService.loadBrands();
        return "Initial data load triggered.";
    }
    
}
