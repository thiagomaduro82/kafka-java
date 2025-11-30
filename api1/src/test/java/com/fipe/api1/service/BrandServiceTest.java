package com.fipe.api1.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fipe.api1.domain.model.Brand;
import com.fipe.api1.infrastructure.cache.BrandCacheService;

public class BrandServiceTest {

    @Mock
    private BrandCacheService brandCacheService;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBrands_ShouldReturnListOfBrands() {
        // Arrange
        List<Brand> mockBrands = List.of(new Brand(1L, "Chevrolet"), new Brand(2L, "Ford"));
        when(brandCacheService.getVehicles()).thenReturn(mockBrands);
        // Act
        List<Brand> result = brandService.getBrands();
        // Assert
        assertEquals(2, result.size());
        assertEquals("Chevrolet", result.get(0).getName());
        assertEquals("Ford", result.get(1).getName());
    }

    @Test
    void getBrands_ShouldThrowException_WhenCacheFails() {
        // Arrange
        when(brandCacheService.getVehicles()).thenThrow(new RuntimeException("Redis offline"));
        // Act & Assert
        assertThrows(RuntimeException.class, () -> brandService.getBrands());
    }

}
