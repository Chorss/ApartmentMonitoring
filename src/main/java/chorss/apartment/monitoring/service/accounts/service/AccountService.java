package chorss.apartment.monitoring.service.accounts.service;

import chorss.apartment.monitoring.service.accounts.objects.AccountBO;

import java.util.UUID;

public interface AccountService {

    void addAccount(AccountBO bo);

    void remove(UUID uuid);
}