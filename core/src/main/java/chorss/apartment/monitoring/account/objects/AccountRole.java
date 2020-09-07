package chorss.apartment.monitoring.account.objects;

public class AccountRole {

    private Long id;
    private String role;
    private String description;

    public Long getId() {
        return id;
    }

    public AccountRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AccountRole setRole(String role) {
        this.role = role;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccountRole setDescription(String description) {
        this.description = description;
        return this;
    }
}