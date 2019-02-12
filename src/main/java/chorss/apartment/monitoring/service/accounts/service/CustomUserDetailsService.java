package chorss.apartment.monitoring.service.accounts.service;

import chorss.apartment.monitoring.service.accounts.entity.Account;
import chorss.apartment.monitoring.service.accounts.entity.AccountRole;
import chorss.apartment.monitoring.service.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository userRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = userRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return getAccount(account);
    }

    private User getAccount(Account account) {
        return new User(account.getEmail(), account.getPassword(), convertAuthorities(account.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Collection<AccountRole> accountRoles) {
        return accountRoles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toSet());
    }
}