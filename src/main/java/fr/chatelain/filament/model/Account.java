package fr.chatelain.filament.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractEntities{
    @Column(unique = true)
    String aliasName;
    @Column
    String firstName;
    @Column
    String lastName;
    @OneToMany
    List<Printer> listPrinter;
}
