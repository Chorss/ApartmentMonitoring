package chorss.apartment.monitoring.account.annotations;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUniqueEmailValidator implements ConstraintValidator<IsUniqueEmail, String> {

    private final AccountRepository accountRepository;

    @Autowired
    public IsUniqueEmailValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void initialize(IsUniqueEmail constraintAnnotation) {
        //Do nothing
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Account account = accountRepository.findByEmail(email);
        return account == null;
    }
}