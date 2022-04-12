package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.BrandFilament;
import fr.chatelain.filament.model.FactoryFilament;
import fr.chatelain.filament.model.Material;
import fr.chatelain.filament.model.Picture;
import fr.chatelain.filament.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandFilamentService implements IGenericService<BrandFilament> {

    private GenericJpaRepository<BrandFilament> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<BrandFilament> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public BrandFilament getInstance(){
        return FactoryFilament.getInstanceBrandFilament();
    }

    public BrandFilament getInstance(String name, String color, boolean silk, boolean diamond, Material material, Picture picture){
        return FactoryFilament.getInstanceBrandFilament(name,color,silk,diamond,material,picture);
    }

    @Override
    public Optional<BrandFilament> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, BrandFilament.class);
    }

    @Override
    public List<BrandFilament> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(BrandFilament.class);
    }

    @Override
    public BrandFilament save(BrandFilament entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, BrandFilament.class);
    }

    @Override
    public BrandFilament update(BrandFilament entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, BrandFilament.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, BrandFilament.class);
    }
}
