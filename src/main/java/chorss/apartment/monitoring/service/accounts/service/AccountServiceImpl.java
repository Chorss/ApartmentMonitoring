package chorss.apartment.monitoring.service.accounts.service;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import chorss.apartment.monitoring.service.accounts.entity.AccountRole;
import chorss.apartment.monitoring.service.accounts.objects.AccountBO;
import chorss.apartment.monitoring.service.accounts.repository.AccountRepository;
import chorss.apartment.monitoring.service.accounts.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void addAccount(AccountBO bo) {
        AccountRole defaultRole = roleRepository.findByRole("ROLE_USER");
        Account account = new Account();
        account.setEmail(bo.getEmail());
        account.setPassword(bCryptPasswordEncoder.encode(bo.getPassword()));
        account.getRoles().add(defaultRole);
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