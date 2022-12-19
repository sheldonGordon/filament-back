package fr.chatelain.filament.service;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Account;
import fr.chatelain.filament.model.FactoryFilament;
import fr.chatelain.filament.model.Printer;
import fr.chatelain.filament.repository.AccountRepository;
import fr.chatelain.filament.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IGenericService<Account> {

    private GenericJpaRepository<Account> genericJpaRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Account> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Account getInstance(){
        return FactoryFilament.getInstanceAccount();
    }

    public Account getInstance(String aliasName, String firstName, String lastName, List<Printer> listPrinter){
        return FactoryFilament.getInstanceAccount(aliasName, firstName, lastName, listPrinter);
    }

    @Override
    public Optional<Account> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Account.class);
    }

    public Account getByAlias(String alias) throws RepositoryExeption {
        return accountRepository.getByAlias(alias);
    }

    @Override
    public List<Account> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Account.class);
    }

    @Override
    public Account save(Account entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Account.class);
    }

    @Override
    public Account update(Account entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Account.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Account.class);
    }
}
