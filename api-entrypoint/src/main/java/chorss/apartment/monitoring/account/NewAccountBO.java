package chorss.apartment.monitoring.account;

import chorss.apartment.monitoring.account.annotations.IsUniqueEmail;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class NewAccountBO {

    @NotNull
    @IsUniqueEmail
    private String email;

    @NotNull
    @Length(min = 4, max = 50)
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}