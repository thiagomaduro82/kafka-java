package com.fipe.api1.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fipe.api1.domain.model.BrandDTO;

@Component
public class BrandProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BrandProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBrands(BrandDTO[] brands) {
        kafkaTemplate.send("brands-topic", brands);
    }

}
