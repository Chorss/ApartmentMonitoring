package chorss.apartment.monitoring.account.service;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.objects.AccountRole;
import chorss.apartment.monitoring.account.repository.AccountRepository;
import chorss.apartment.monitoring.account.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AccountServiceImpl(AccountRepository accountRepository, AccountRoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void addAccount(String email, String password) {
        AccountRole defaultRole = roleRepository.findByRole("ROLE_USER");
        List<AccountRole> roles = new ArrayList<>();
        roles.add(defaultRole);

        Account account = new Account()
                .setEmail(email)
                .setPassword(bCryptPasswordEncoder.encode(password))
                .setRoles(roles);

        accountRepository.save(account);
    }

    @Override
    public void remove(UUID uuid) {
        Account account = accountRepository.findByUuid(uuid);

        if (account != null) {
            accountRepository.delete(account);
        }
    }
}