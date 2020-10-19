package ru.cgmd.accounting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.cgmd.accounting_system.domain.Role;
import ru.cgmd.accounting_system.domain.User;
import ru.cgmd.accounting_system.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("listUser", userService.findAll());
        return "user_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user_edit";
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

        return "redirect:/user";
    }

    /**
     @GetMapping("profile")
     public String profile(Model model, @AuthenticationPrincipal User user) {
     model.addAttribute("username", user.getUsername());
     return "profile";
     }

     @PostMapping("profile")
     public String updateProfile(
     @AuthenticationPrincipal User user,
     @RequestParam String password
     ) {
     userService.updateProfile(user, password);

     return "redirect:/user/profile";
     }
     /**/
}