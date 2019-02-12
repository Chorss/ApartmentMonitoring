package chorss.apartment.monitoring.service.accounts.repository;

import chorss.apartment.monitoring.service.accounts.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {

    AccountRole findByRole(String role);
}