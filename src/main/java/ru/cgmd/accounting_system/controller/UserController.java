package ru.cgmd.accounting_system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.cgmd.accounting_system.domain.Role;
import ru.cgmd.accounting_system.domain.User;
import ru.cgmd.accounting_system.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("listUser", userService.findAll());
        return "user_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, password, form);

        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user_edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("activate/{user}")
    public String userActivate(@PathVariable User user) {
        if (user.isActive()) {
            user.setActive(false);
        } else user.setActive(true);

        userService.saveUser(user);
        return "redirect:/users";
    }

    /**/
    @GetMapping("profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String password) {
        userService.updateProfile(user, password);
        return "redirect:/users/profile";
    }
    /**/
}