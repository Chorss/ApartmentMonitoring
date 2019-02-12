package chorss.apartment.monitoring.service.accounts.repository;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUuid(UUID uuid);

    Account findByUuidAndName(UUID uuid, String name);

    Account findByEmail(String email);
}