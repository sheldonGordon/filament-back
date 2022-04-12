package fr.chatelain.filament.repository;

import fr.chatelain.filament.model.AbstractEntities;
import org.springframework.stereotype.Repository;

@Repository
public class GenericJpaRepository<T extends AbstractEntities> extends AbstractJpaRepository<T> {
}
