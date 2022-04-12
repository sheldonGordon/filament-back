package fr.chatelain.filament.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Picture extends AbstractEntities{
    @Column(columnDefinition = "text")
    private String data;

    @Column
    private String name;

    @Column
    private String typeMime;
}
