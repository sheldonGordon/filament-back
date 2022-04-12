package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.BrandFilament;
import fr.chatelain.filament.model.FactoryFilament;
import fr.chatelain.filament.model.Filament;
import fr.chatelain.filament.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilamentService implements IGenericService<Filament> {

    private GenericJpaRepository<Filament> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Filament> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Filament getInstance(){
        return FactoryFilament.getInstanceFilament();
    }

    public Filament getInstance(BrandFilament brand, int temperature, double extrusionMultiplier, int bedTemperature, int withdrawal){
        return FactoryFilament.getInstanceFilament(brand,temperature,extrusionMultiplier,bedTemperature,withdrawal);
    }

    @Override
    public Optional<Filament> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Filament.class);
    }

    @Override
    public List<Filament> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Filament.class);
    }

    @Override
    public Filament save(Filament entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Filament.class);
    }

    @Override
    public Filament update(Filament entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Filament.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Filament.class);
    }
}
