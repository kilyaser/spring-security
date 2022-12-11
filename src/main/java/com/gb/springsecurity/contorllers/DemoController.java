package com.gb.springsecurity.contorllers;

import com.gb.springsecurity.entities.User;
import com.gb.springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }
    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }
    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(RuntimeException::new);
        return "Authenticated user: " + user.getUsername() + " : " + user.getEmail();
    }
}
