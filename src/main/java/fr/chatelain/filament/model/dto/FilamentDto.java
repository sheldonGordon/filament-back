package fr.chatelain.filament.model.dto;

import lombok.*;

import javax.persistence.Column;

@Data
public class FilamentDto {
    private String id;
    private BrandFilamentDto brand;
    private double extrusionMultiplier;
    private int bedTemperatureFirstLayer;
    private int bedTemperatureOtherLayer;
    private int extruderTemperatureFirstLayer;
    private int extruderTemperatureOtherLayer;
    private int lengthRetraction;
}
