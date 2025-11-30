package com.fipe.api2.infrastructure.kafka;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe.api2.application.FipeConsumerService;
import com.fipe.api2.domain.BrandDTO;
import com.fipe.api2.domain.Event;
import com.fipe.api2.infrastructure.repository.EventRepository;

@Component
public class BrandConsumer {

    private final FipeConsumerService fipeConsumerService;
    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;

    public BrandConsumer(FipeConsumerService fipeConsumerService, ObjectMapper objectMapper,
            EventRepository eventRepository) {
        this.fipeConsumerService = fipeConsumerService;
        this.objectMapper = objectMapper;
        this.eventRepository = eventRepository;
    }

    @KafkaListener(topics = "brands-topic", groupId = "api2-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeBrand(String payload) {
        // Create the event record
        Event event = new Event(null, "brands-topic", payload, "RECEIVED", Timestamp.valueOf(LocalDateTime.now()));
        event = eventRepository.save(event);
        try {
            // Deserialize JSON payload to BrandDTO array
            BrandDTO[] brands = objectMapper.readValue(payload, BrandDTO[].class);

            for (BrandDTO brand : brands) {
                fipeConsumerService.processBrand(brand);
            }
            // Update event status to PROCESSED
            event.setStatus("PROCESSED");
            eventRepository.save(event);
        } catch (Exception e) {
            // Update event status to FAILED
            event.setStatus("FAILED");
            eventRepository.save(event);
            throw new RuntimeException("Error deserializing or processing Kafka brands.", e);
        }

    }

}
