package chorss.apartment.monitoring.controller;

import chorss.apartment.monitoring.service.accounts.objects.AccountBO;
import chorss.apartment.monitoring.service.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@Controller
class AccountController {

    private final AccountService accountService;

    @Autowired
    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute(new AccountBO());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid AccountBO accountBO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        accountService.addAccount(accountBO);
        return "redirect:/home";
    }

    @RequestMapping(value = "/account/remove/{uuid}", method = RequestMethod.GET)
    public String registration(@PathVariable String uuid, Model model) {

        accountService.remove(UUID.fromString(uuid));
        return "redirect:/home";
    }
}