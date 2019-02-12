package chorss.apartment.monitoring.service.accounts.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "password", length = 250, nullable = false)
    @NotNull
    private String password;

    @Column(name = "name", length = 250, nullable = true)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<AccountRole> roles = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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