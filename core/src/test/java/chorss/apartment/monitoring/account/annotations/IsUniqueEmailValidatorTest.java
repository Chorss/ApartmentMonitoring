package chorss.apartment.monitoring.account.annotations;

import chorss.apartment.monitoring.account.objects.Account;
import chorss.apartment.monitoring.account.objects.AccountRole;
import chorss.apartment.monitoring.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IsUniqueEmailValidatorTest {

    private static final String DOMAIN = "@zxc.com";
    private static final String MIKI = "miki";
    private static final String BOB = "bob";

    private static final String EMAIL_MIKI = MIKI + DOMAIN;
    private static final String EMAIL_BOB = BOB + DOMAIN;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private IsUniqueEmailValidator mock;

    @Test
    void shouldNotUniqueEmail() {
        // Given
        Account mikiAccount = createAccount(MIKI, EMAIL_MIKI, null);
        when(accountRepository.findByEmail(EMAIL_MIKI)).thenReturn(mikiAccount);

        // When
        boolean result = mock.isValid(EMAIL_MIKI, null);

        // Then
        assertFalse(result);
    }

    @Test
    void shouldUniqueEmail() {
        // Given
        when(accountRepository.findByEmail(EMAIL_BOB)).thenReturn(null);

        // When
        boolean result = mock.isValid(EMAIL_BOB, null);

        // Then
        assertTrue(result);
    }

    private Account createAccount(String name, String email, List<AccountRole> roles) {
        return new Account()
                .setName(name)
                .setEmail(email)
                .setRoles(roles);
    }
}