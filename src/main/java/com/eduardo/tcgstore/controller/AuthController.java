package com.eduardo.tcgstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Controller responsible for authentication views.
 *
 * Handles:
 * - Login page
 * - User registration page
 *
 * It connects the UI with the authentication system.
 */
@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }
}