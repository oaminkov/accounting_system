package ru.cgmd.accounting_system.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.cgmd.accounting_system.domain.Role;
import ru.cgmd.accounting_system.domain.User;
import ru.cgmd.accounting_system.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    public void isUserAuthorized(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("loggedUser", user);
        } else {
            model.addAttribute("message", "Вы не авторизованы!");
        }
    }

    public void isAdminExists() {
        UserDetails userFromDb = userService.loadUserByUsername("admin");

        if (userFromDb == null) {
            User user = new User();

            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);

            user.setUsername("admin");
            user.setPassword("qwe");
            user.setActive(true);
            user.setRoles(roles);
            userService.addUser(user);
        }
    }

    @GetMapping("/")
    public String redirectMainPage(@AuthenticationPrincipal User user, Model model) {
        isUserAuthorized(user, model);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String viewMainPage(@AuthenticationPrincipal User user, Model model) {
        isAdminExists(); //если админа не существует, добавляем
        isUserAuthorized(user, model);
        return "main";
    }

    @GetMapping("/login")
    public String viewLoginPage(@AuthenticationPrincipal User loggedUser, Model model) {
        if (loggedUser != null) {
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("message", "Вы уже авторизованы *o*");
        } else {
            model.addAttribute("message", "Авторизуйтесь ^_^");
        }
        return ("login");
    }
}
