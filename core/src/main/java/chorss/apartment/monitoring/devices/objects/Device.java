package chorss.apartment.monitoring.devices.objects;

import chorss.apartment.monitoring.account.objects.Account;

import java.time.LocalDateTime;
import java.util.UUID;

public class Device {
    private UUID uuid;
    private String name;
    private Account account;
    private LocalDateTime created;

    public UUID getUuid() {
        return uuid;
    }

    public Device setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Device setName(String name) {
        this.name = name;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Device setAccount(Account account) {
        this.account = account;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Device setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}