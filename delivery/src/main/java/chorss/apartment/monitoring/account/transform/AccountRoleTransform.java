package chorss.apartment.monitoring.account.transform;

import chorss.apartment.monitoring.account.entity.AccountRoleEntity;
import chorss.apartment.monitoring.account.jpa.JpaAccountRoleRepository;
import chorss.apartment.monitoring.account.objects.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AccountRoleTransform {

    private final JpaAccountRoleRepository jpaAccountRoleRepository;

    @Autowired
    public AccountRoleTransform(JpaAccountRoleRepository jpaAccountRoleRepository) {
        this.jpaAccountRoleRepository = jpaAccountRoleRepository;
    }

    public AccountRoleEntity map(AccountRole role) {
        AccountRoleEntity accountRoleEntity = jpaAccountRoleRepository.findByRole(role.getRole());

        if (accountRoleEntity != null) {
            return accountRoleEntity;
        }

        AccountRoleEntity entity = new AccountRoleEntity();
        entity.setRole(role.getRole());
        entity.setDescription(role.getDescription());

        return entity;
    }


    public AccountRole map(AccountRoleEntity entity) {
        if (entity == null) {
            return null;
        }

        return new AccountRole()
                .setRole(entity.getRole())
                .setDescription(entity.getDescription());
    }
}