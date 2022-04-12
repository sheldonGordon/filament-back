package fr.chatelain.filament.model.dto;

import fr.chatelain.filament.model.Material;
import lombok.Data;

@Data
public class BrandFilamentDto {
    private String id;
    private String name;
    private String color;
    private boolean silk;
    private boolean diamond;
    private Material material;
    private PictureDto picture;
}
