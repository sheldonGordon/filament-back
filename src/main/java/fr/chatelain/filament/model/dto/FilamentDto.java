package fr.chatelain.filament.model.dto;

import lombok.*;

@Data
public class FilamentDto {
    private String id;
    private BrandFilamentDto brand;
    private int temperature;
    private double extrusionMultiplier;
    private int bedTemperature;
    private int withdrawal;
}
