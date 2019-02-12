package chorss.apartment.monitoring.service.accounts.objects;

import chorss.apartment.monitoring.service.accounts.entity.AccountRole;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {

    private String uuid;
    private String email;
    private String name;
    private List<AccountRole> roles = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AccountRole> roles) {
        this.roles = roles;
    }
}