package com.fipe.api1.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fipe.api1.domain.model.BrandDTO;

@Component
public class BrandProducer {

    private final KafkaTemplate<String, BrandDTO> kafkaTemplate;

    public BrandProducer(KafkaTemplate<String, BrandDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBrand(BrandDTO brandDTO) {
        kafkaTemplate.send("brands-topic", brandDTO);
    }
    
}
