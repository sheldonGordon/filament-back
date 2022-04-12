package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.FactoryFilament;
import fr.chatelain.filament.model.Filament;
import fr.chatelain.filament.model.Picture;
import fr.chatelain.filament.model.Printer;
import fr.chatelain.filament.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrinterService implements IGenericService<Printer> {

    private GenericJpaRepository<Printer> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Printer> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Printer getInstance(){
        return FactoryFilament.getInstancePrinter();
    }

    public Printer getInstance(String name, String model, Picture picture, List<Filament> listFilament){
        return FactoryFilament.getInstancePrinter(name,model,picture,listFilament);
    }

    @Override
    public Optional<Printer> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Printer.class);
    }

    @Override
    public List<Printer> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Printer.class);
    }

    @Override
    public Printer save(Printer entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Printer.class);
    }

    @Override
    public Printer update(Printer entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Printer.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Printer.class);
    }
}
