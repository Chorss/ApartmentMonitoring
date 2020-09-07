package chorss.apartment.monitoring.account.repository;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import chorss.apartment.monitoring.account.jpa.JpaAccountRepository;
import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.transform.AccountTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final AccountTransform accountTransform;

    @Autowired
    public AccountRepositoryImpl(JpaAccountRepository jpaAccountRepository, AccountTransform accountTransform) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.accountTransform = accountTransform;
    }

    @Override
    public Account findByUuid(UUID uuid) {
        return accountTransform.map(jpaAccountRepository.findByUuid(uuid));
    }

    @Override
    public Account findByUuidAndName(UUID uuid, String name) {
        return accountTransform.map(jpaAccountRepository.findByUuidAndName(uuid, name));
    }

    @Override
    public Account findByEmail(String email) {
        return accountTransform.map(jpaAccountRepository.findByEmail(email));
    }

    @Override
    public void save(Account account) {
        AccountEntity entity = accountTransform.map(account);
        jpaAccountRepository.save(entity);
    }

    @Override
    public void delete(Account account) {
        jpaAccountRepository.delete(accountTransform.map(account));
    }
}