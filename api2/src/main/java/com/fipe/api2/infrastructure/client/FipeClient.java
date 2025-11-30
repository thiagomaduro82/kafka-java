package com.fipe.api2.infrastructure.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FipeClient {

    private final RestClient restClient;

    public FipeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Map getModelsByBrandId(String brandCodigo) {
        String url = String.format("https://parallelum.com.br/fipe/api/v1/carros/marcas/%s/modelos", brandCodigo);
        return restClient.get().uri(url).retrieve().body(Map.class);
    }   
    
}
