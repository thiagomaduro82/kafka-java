package com.fipe.api2.application;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fipe.api2.domain.Brand;
import com.fipe.api2.domain.Model;
import com.fipe.api2.infrastructure.client.FipeClient;
import com.fipe.api2.infrastructure.repository.ModelRepository;

@Service
public class FipeConsumerService {

    private final FipeClient fipeClient;
    private final ModelRepository modelRepository;

    public FipeConsumerService(FipeClient fipeClient, ModelRepository modelRepository) {
        this.fipeClient = fipeClient;
        this.modelRepository = modelRepository;
    }

    public void processBrand(Brand brand) {

        Map response = fipeClient.getModelsByBrandId(brand.id());

        List<Map<String, Object>> models = (List<Map<String, Object>>) response.get("modelos");

        for (Map<String, Object> modelData : models) {
            Long modelId = (Long) modelData.get("codigo");
            String modelName = (String) modelData.get("nome");

            Model model = new Model(modelId, modelName, brand.id(), brand.name());

            modelRepository.save(model);
        }
        
    }
    
}
