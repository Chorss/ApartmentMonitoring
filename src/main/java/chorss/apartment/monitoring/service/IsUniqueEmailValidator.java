package chorss.apartment.monitoring.service;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import chorss.apartment.monitoring.service.accounts.repository.AccountRepository;
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
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Account account = accountRepository.findByEmail(email);
        return account == null;
    }
}