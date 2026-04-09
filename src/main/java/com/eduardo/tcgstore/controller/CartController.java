package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.CartItemView;
import com.eduardo.tcgstore.model.Customer;
import com.eduardo.tcgstore.service.CartService;
import com.eduardo.tcgstore.service.CustomerService;
import com.eduardo.tcgstore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller responsible for the public shopping cart flow.
 *
 * Handles:
 * - Viewing cart contents
 * - Adding/removing products
 * - Clearing the cart
 * - Checkout process
 *
 * Integrates session-based cart with order creation.
 */
@Controller
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public CartController(CartService cartService,
                          CustomerService customerService,
                          OrderService orderService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        model.addAttribute("cartItems", cartService.getCartItems(session));
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        return "cart/view";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session,
                            @RequestHeader(value = "referer", required = false) String referer,
                            RedirectAttributes redirectAttributes) {
        try {
            cartService.addProduct(session, productId, quantity);
            redirectAttributes.addFlashAttribute("successMessage", "Product added to cart successfully.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:" + (referer != null ? referer : "/products");
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        cartService.removeProduct(session, productId);
        redirectAttributes.addFlashAttribute("successMessage", "Product removed from cart.");
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session,
                            RedirectAttributes redirectAttributes) {
        cartService.clearCart(session);
        redirectAttributes.addFlashAttribute("successMessage", "Cart cleared successfully.");
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkoutCart(HttpSession session,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }

        try {
            List<CartItemView> cartItems = cartService.getCartItems(session);

            if (cartItems.isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Your cart is empty.");
                return "redirect:/cart";
            }

            Customer customer = customerService.findOrCreateCustomerFromUsername(authentication.getName());
            orderService.checkoutCart(customer, cartItems);
            cartService.clearCart(session);

            redirectAttributes.addFlashAttribute("successMessage", "Purchase completed successfully.");
            return "redirect:/cart";
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cart";
        }
    }
}