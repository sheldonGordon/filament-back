package fr.chatelain.filament.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrinterDto {
    private String id;
    private String name;
    private String model;
    private PictureDto picture;
    List<FilamentDto> listFilament;
}
