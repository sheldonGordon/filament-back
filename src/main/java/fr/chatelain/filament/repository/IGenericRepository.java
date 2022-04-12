package fr.chatelain.filament.repository;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.AbstractEntities;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IGenericRepository<T extends AbstractEntities> {
    Optional<T> getById(final String id, Class<T> type) throws RepositoryExeption;

    List<T> findAll(Class<T> type) throws RepositoryExeption;

    T save(final T entity, Class<T> type) throws RepositoryExeption;

    T update(final T entity, Class<T> type) throws RepositoryExeption;

    void deleteById(final String id, Class<T> type) throws RepositoryExeption;
}
