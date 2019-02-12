package chorss.apartment.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/hello")
    public String userIndex() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String accessDenied() {
        return "home";
    }
}