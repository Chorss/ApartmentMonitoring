package chorss.apartment.monitoring.account.transform;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import chorss.apartment.monitoring.account.objects.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public final class AccountTransform {

    private final AccountRoleTransform accountRoleTransform;

    @Autowired
    public AccountTransform(AccountRoleTransform accountRoleTransform) {
        this.accountRoleTransform = accountRoleTransform;
    }

    public AccountEntity map(Account account) {
        AccountEntity entity = new AccountEntity();

        entity.setUuid(account.getUuid());
        entity.setName(account.getName());
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());
        entity.setRoles(account.getRoles()
                .stream()
                .filter(Objects::nonNull)
                .map(accountRoleTransform::map)
                .collect(Collectors.toList()
                ));

        return entity;
    }

    public Account map(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Account()
                .setUuid(entity.getUuid())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .setPassword(entity.getPassword())
                .setRoles(entity.getRoles()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(accountRoleTransform::map)
                        .collect(Collectors.toList()));
    }
}