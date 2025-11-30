package com.fipe.api1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fipe.api1.domain.model.BrandDTO;
import com.fipe.api1.infrastructure.http.FipeClient;
import com.fipe.api1.infrastructure.kafka.BrandProducer;

public class InitialLoadServiceTest {

    @Mock
    private FipeClient fipeClient;

    @Mock
    private BrandProducer brandProducer;

    @InjectMocks
    private InitialLoadService initialLoadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadBrands_ShouldFetchAndSendBrands() {
        // Arrange
        BrandDTO[] mockBrands = { new BrandDTO("1", "Chevrolet"), new BrandDTO("2", "Ford") };
        when(fipeClient.fetchBrands()).thenReturn(mockBrands);
        // Act
        initialLoadService.loadBrands();
        // Assert
        verify(fipeClient, times(1)).fetchBrands();
        verify(brandProducer, times(1)).sendBrands(mockBrands);
    }

    @Test
    void loadBrands_ShouldThrowException_WhenFipeClientFails() {
        // Arrange
        when(fipeClient.fetchBrands()).thenThrow(new RuntimeException("API unavailable"));

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> initialLoadService.loadBrands()
        );

        assertEquals("API unavailable", exception.getMessage());

        // Make sure sendBrands is never called
        verify(brandProducer, never()).sendBrands(any());
    }
    
}
