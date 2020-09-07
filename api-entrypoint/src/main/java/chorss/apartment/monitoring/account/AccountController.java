package chorss.apartment.monitoring.account;

import chorss.apartment.monitoring.account.service.AccountService;
import chorss.apartment.monitoring.account.service.LoggedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

@ApiIgnore
@Controller
class AccountController {

    private static final String ACCOUNT_REGISTER_PAGE = "account/register";
    private static final String ACCOUNT_DETAILS_PAGE = "account/details";
    private static final String REDIRECT_MAIN_PAGE = "redirect:/";

    @Value("${isEnabledRegistration}")
    private boolean isEnabledRegistration;

    private final AccountService accountService;
    private final LoggedAccountService loggedAccountService;

    @Autowired
    AccountController(AccountService accountService, LoggedAccountService loggedAccountService) {
        this.accountService = accountService;
        this.loggedAccountService = loggedAccountService;
    }

    @GetMapping(value = "/register")
    public String registration(Model model) {
        model.addAttribute("isEnabledRegistration", isEnabledRegistration);
        model.addAttribute(new NewAccountBO());
        return ACCOUNT_REGISTER_PAGE;
    }

    @PostMapping(value = "/register")
    public String registration(@Valid NewAccountBO newAccount, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors() || !isEnabledRegistration) {
            model.addAttribute("isEnabledRegistration", isEnabledRegistration);
            return ACCOUNT_REGISTER_PAGE;
        }

        accountService.addAccount(newAccount.getEmail(), newAccount.getPassword());
        return REDIRECT_MAIN_PAGE;
    }

    @GetMapping(value = "/account/remove/{uuid}")
    public String remove(@PathVariable String uuid) {
        accountService.remove(UUID.fromString(uuid));
        return REDIRECT_MAIN_PAGE;
    }

    @GetMapping(value = "/account/details")
    public String accountDetails(Model model) {
        model.addAttribute("account", loggedAccountService.getAccount());

        return ACCOUNT_DETAILS_PAGE;
    }
}