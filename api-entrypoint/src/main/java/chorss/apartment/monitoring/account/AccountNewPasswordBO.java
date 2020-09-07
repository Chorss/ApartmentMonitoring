package chorss.apartment.monitoring.account;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AccountNewPasswordBO {

    @NotNull
    @Length(min = 4, max = 50)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}