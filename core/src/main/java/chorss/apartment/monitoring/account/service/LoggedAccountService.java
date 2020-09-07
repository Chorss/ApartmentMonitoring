package chorss.apartment.monitoring.account.service;


import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class LoggedAccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public LoggedAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount() {
        return accountRepository.findByEmail(getUsername());
    }

    private String getUsername() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}