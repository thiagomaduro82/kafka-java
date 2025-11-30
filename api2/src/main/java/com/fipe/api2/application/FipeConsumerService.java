package com.fipe.api2.application;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fipe.api2.domain.Brand;
import com.fipe.api2.domain.BrandDTO;
import com.fipe.api2.domain.Vehicle;
import com.fipe.api2.infrastructure.client.FipeClient;
import com.fipe.api2.infrastructure.repository.BrandRepository;
import com.fipe.api2.infrastructure.repository.VehicleRepository;

@Service
public class FipeConsumerService {

    private final FipeClient fipeClient;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;

    public FipeConsumerService(FipeClient fipeClient, VehicleRepository vehicleRepository,
            BrandRepository brandRepository) {
        this.fipeClient = fipeClient;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
    }

    public void processBrand(BrandDTO brandDTO) {

        Brand brand = new Brand(Long.valueOf(brandDTO.codigo()), brandDTO.nome());
        brand = brandRepository.save(brand);

        Map response = fipeClient.getModelsByBrandId(brandDTO.codigo());

        List<Map<String, Object>> models = (List<Map<String, Object>>) response.get("modelos");

        for (Map<String, Object> modelData : models) {
            Vehicle vehicle = new Vehicle(Long.valueOf((Integer) modelData.get("codigo")),
                    (String) modelData.get("nome"), brand, null);
            vehicleRepository.save(vehicle);
        }
        
    }
    
}
