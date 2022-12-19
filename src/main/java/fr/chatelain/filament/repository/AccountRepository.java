package fr.chatelain.filament.repository;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Service
public class AccountRepository extends AbstractJpaRepository<Account>{

    @Autowired
    private EntityManager em;

    public Account getByAlias(String alias) throws RepositoryExeption {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cr = cb.createQuery(Account.class);

        Root<Account> account = cr.from(Account.class);

        cr.select(account);
        cr.where(cb.equal(account.get("aliasName"), alias));

        TypedQuery<Account> query = em.createQuery(cr);

        if(query.getResultList().isEmpty()){
            throw new RepositoryExeption(
                    String.format("Aucun objet trouvé de type Account pour l'alias %s", alias));
        }

        return query.getSingleResult();
    }
}
