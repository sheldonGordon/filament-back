package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T> {
    Optional<T> getById(final String id) throws RepositoryExeption;

    List<T> findAll() throws RepositoryExeption;

    T save(final T entity) throws RepositoryExeption;

    T update(final T entity) throws RepositoryExeption;

    void deleteById(final String id) throws RepositoryExeption;
}
