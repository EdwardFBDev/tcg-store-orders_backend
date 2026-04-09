package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.User;
import com.eduardo.tcgstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.registerRegularUser(user);
            model.addAttribute("successMessage", "User registered successfully. You can now log in.");
            model.addAttribute("user", new User());
            return "users/register";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user);
            return "users/register";
        }
    }
}