package com.fipe.api2.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fipe.api2.application.FipeConsumerService;
import com.fipe.api2.domain.Brand;

@Component
public class BrandConsumer {

    private final FipeConsumerService fipeConsumerService;

    public BrandConsumer(FipeConsumerService fipeConsumerService) {
        this.fipeConsumerService = fipeConsumerService;
    }

    @KafkaListener(topics = "brands", groupId = "api2-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeBrand(Brand brand) {
        fipeConsumerService.processBrand(brand);
    }
    
}
