package chorss.apartment.monitoring.account.repository;

import chorss.apartment.monitoring.account.objects.AccountRole;

public interface AccountRoleRepository {

    AccountRole findByRole(String role);
}