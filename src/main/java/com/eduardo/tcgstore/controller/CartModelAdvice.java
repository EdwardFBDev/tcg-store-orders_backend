package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CartModelAdvice {

    private final CartService cartService;

    public CartModelAdvice(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("cartItemCount")
    public int cartItemCount(HttpSession session) {
        return cartService.getCartItemCount(session);
    }
}