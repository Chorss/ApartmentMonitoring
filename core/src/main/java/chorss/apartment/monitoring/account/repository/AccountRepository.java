package chorss.apartment.monitoring.account.repository;

import chorss.apartment.monitoring.account.objects.Account;

import java.util.UUID;

public interface AccountRepository {

    Account findByUuid(UUID uuid);

    Account findByUuidAndName(UUID uuid, String name);

    Account findByEmail(String email);

    void save(Account account);

    void delete(Account account);
}