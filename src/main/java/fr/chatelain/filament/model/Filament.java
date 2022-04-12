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
    private int temperature;
    @Column
    private double extrusionMultiplier;
    @Column
    private int bedTemperature;
    @Column
    private int withdrawal;
}
