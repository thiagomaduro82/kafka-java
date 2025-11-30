package com.fipe.api1.service;

import org.springframework.stereotype.Service;

import com.fipe.api1.domain.model.BrandDTO;
import com.fipe.api1.infrastructure.http.FipeClient;
import com.fipe.api1.infrastructure.kafka.BrandProducer;

@Service
public class InitialLoadService {

    private final FipeClient fipeClient;
    private final BrandProducer brandProducer;

    public InitialLoadService(FipeClient fipeClient, BrandProducer brandProducer) {
        this.fipeClient = fipeClient;
        this.brandProducer = brandProducer;
    }

    public void loadBrands() {
        BrandDTO[] brands = fipeClient.fetchBrands();
        brandProducer.sendBrands(brands);
    }

}
