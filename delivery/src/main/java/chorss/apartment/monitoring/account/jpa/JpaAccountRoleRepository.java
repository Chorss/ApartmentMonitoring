package chorss.apartment.monitoring.account.jpa;

import chorss.apartment.monitoring.account.entity.AccountRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {

    AccountRoleEntity findByRole(String role);
}