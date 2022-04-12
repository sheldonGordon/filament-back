package fr.chatelain.filament.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntities implements Serializable {
    protected AbstractEntities() {
        this.id = UUID.randomUUID().toString();
    }
    @Id
    @Getter
    @Setter
    private String id;
}
