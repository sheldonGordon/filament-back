package fr.chatelain.filament.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class BrandFilament extends AbstractEntities {
    @Column
    private String name;
    @Column
    private String color;
    @Column
    private boolean silk;
    @Column
    private boolean diamond;
    @Enumerated(EnumType.STRING)
    private Material material;
    @OneToOne
    private Picture picture;
}
