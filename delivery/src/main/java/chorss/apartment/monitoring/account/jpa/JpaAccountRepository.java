package chorss.apartment.monitoring.account.jpa;

import chorss.apartment.monitoring.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByUuid(UUID uuid);

    AccountEntity findByUuidAndName(UUID uuid, String name);

    AccountEntity findByEmail(String email);

    boolean existsByUuid(UUID uuid);
}