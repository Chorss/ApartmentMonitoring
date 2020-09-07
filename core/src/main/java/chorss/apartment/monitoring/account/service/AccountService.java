package chorss.apartment.monitoring.account.service;

import java.util.UUID;

public interface AccountService {

    void addAccount(String email, String password);

    void remove(UUID uuid);
}