package com.fipe.api1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api1.service.InitialLoadService;

@RestController
public class LoadController {

    private final InitialLoadService initialLoadService;

    public LoadController(InitialLoadService initialLoadService) {
        this.initialLoadService = initialLoadService;
    }

    // Endpoint to trigger the initial load
    @GetMapping("/load-initial-data")
    public String loadInitialData() {
        initialLoadService.loadBrands();
        return "Initial data load triggered.";
    }
    
}
