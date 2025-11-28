package com.fipe.api2.infrastructure.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fipe.api2.domain.Model;
import com.fipe.api2.infrastructure.repository.ModelRepository;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = modelRepository.findAll();
        return ResponseEntity.ok().body(models);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Model>> getModelsByBrandId(@PathVariable Integer brandId) {
        List<Model> models = modelRepository.findByBrandId(brandId);
        return ResponseEntity.ok().body(models);
    }
    
}
