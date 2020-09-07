package chorss.apartment.monitoring.account.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    private UUID uuid;
    private String email;
    private String name;
    private String password;
    private List<AccountRole> roles = new ArrayList<>();

    public UUID getUuid() {
        return uuid;
    }

    public Account setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<AccountRole> getRoles() {
        return roles;
    }

    public Account setRoles(List<AccountRole> roles) {
        this.roles = roles;
        return this;
    }
}