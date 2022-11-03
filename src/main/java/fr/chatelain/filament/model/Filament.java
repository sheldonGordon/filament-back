package fr.chatelain.filament.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Filament extends AbstractEntities{
    @OneToOne
    private BrandFilament brand;
    @Column
    private double extrusionMultiplier;
    @Column
    private int bedTemperatureFirstLayer;
    @Column
    private int bedTemperatureOtherLayer;
    @Column
    private int extruderTemperatureFirstLayer;
    @Column
    private int extruderTemperatureOtherLayer;
    @Column
    private int lengthRetraction;
}
