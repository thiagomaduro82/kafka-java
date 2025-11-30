package com.fipe.api1.domain.model;

public class VehicleDTO {

    private String modelName;
    private String observation;

    public VehicleDTO() {
        super();
    }

    public VehicleDTO(String modelName, String observation) {
        this.modelName = modelName;
        this.observation = observation;
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
    
}
