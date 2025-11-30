package com.fipe.api2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    
    @Id
    private Long id;

    @Column(name = "model_name")
    private String modelName;
    
    private String observation;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Vehicle() {
        super();
    }

    public Vehicle(Long id, String modelName, Brand brand, String observation) {
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
        this.observation = observation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

}
