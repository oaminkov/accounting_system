package ru.cgmd.accounting_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.cgmd.accounting_system.domain.User;
import ru.cgmd.accounting_system.service.UserService;

import java.util.Map;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(user.getPassword().trim());

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty())
        {
            model.put("message", "Имя пользователя и пароль должны содержать хотя бы 1 непробельный символ.");
            return "registration";
        }
        if (!userService.addUser(user)){
            model.put("message", "Пользователь с таким именем уже существует!");
            return "registration";
        }
        return "redirect:/users";
    }
}