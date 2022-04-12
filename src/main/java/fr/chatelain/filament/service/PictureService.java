package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.FactoryFilament;
import fr.chatelain.filament.model.Picture;
import fr.chatelain.filament.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PictureService implements IGenericService<Picture> {

    private GenericJpaRepository<Picture> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Picture> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Picture getInstance(){
        return FactoryFilament.getInstancePicture();
    }

    public Picture getInstance(String data, String name, String typeMime){
        return FactoryFilament.getInstancePicture(data,name,typeMime);
    }

    @Override
    public Optional<Picture> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Picture.class);
    }

    @Override
    public List<Picture> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Picture.class);
    }

    @Override
    public Picture save(Picture entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Picture.class);
    }

    @Override
    public Picture update(Picture entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Picture.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Picture.class);
    }
}
