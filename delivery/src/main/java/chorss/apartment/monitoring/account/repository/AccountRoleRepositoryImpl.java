package chorss.apartment.monitoring.account.repository;

import chorss.apartment.monitoring.account.jpa.JpaAccountRoleRepository;
import chorss.apartment.monitoring.account.objects.AccountRole;
import chorss.apartment.monitoring.account.transform.AccountRoleTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleRepositoryImpl implements AccountRoleRepository {

    private final JpaAccountRoleRepository jpaAccountRoleRepository;
    private final AccountRoleTransform accountRoleTransform;

    @Autowired
    public AccountRoleRepositoryImpl(JpaAccountRoleRepository jpaAccountRoleRepository, AccountRoleTransform accountRoleTransform) {
        this.jpaAccountRoleRepository = jpaAccountRoleRepository;
        this.accountRoleTransform = accountRoleTransform;
    }

    @Override
    public AccountRole findByRole(String role) {
        return accountRoleTransform.map(jpaAccountRoleRepository.findByRole(role));
    }
}