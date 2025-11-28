package com.fipe.api1.infrastructure.http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fipe.api1.domain.model.BrandDTO;

@Component
public class FipeClient {

    private static final String BRAND_URL = "https://parallelum.com.br/fipe/api/v1/carros/marcas";

    private final RestTemplate restTemplate = new RestTemplate();

    public BrandDTO[] fetchBrands() {
        return restTemplate.getForObject(BRAND_URL, BrandDTO[].class);
    }
    
}
