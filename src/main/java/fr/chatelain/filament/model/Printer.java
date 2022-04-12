package fr.chatelain.filament.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Printer extends AbstractEntities{
    @Column
    private String name;
    @Column
    private String model;
    @OneToOne
    private Picture picture;
    @OneToMany
    private List<Filament> listFilament;
}
