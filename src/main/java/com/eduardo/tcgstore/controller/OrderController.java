package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.Customer;
import com.eduardo.tcgstore.service.CustomerService;
import com.eduardo.tcgstore.service.OrderService;
import com.eduardo.tcgstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService,
                           CustomerService customerService,
                           ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/list";
    }

    @GetMapping("/orders/new")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "orders/form";
    }

    @PostMapping("/orders")
    public String createOrder(@RequestParam Long customerId, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getCustomerById(customerId);
            orderService.createOrder(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Order created successfully.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}")
    public String showOrderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "orders/detail";
    }

    @PostMapping("/orders/{id}/items")
    public String addItemToOrder(@PathVariable Long id,
                                 @RequestParam Long productId,
                                 @RequestParam int quantity,
                                 RedirectAttributes redirectAttributes) {
        try {
            orderService.addItemToOrder(id, productId, quantity);
            redirectAttributes.addFlashAttribute("successMessage", "Item added successfully.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/orders/" + id;
    }

    @PostMapping("/orders/{id}/confirm")
    public String confirmOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderService.confirmOrder(id);
            redirectAttributes.addFlashAttribute("successMessage", "Order confirmed successfully.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/orders/" + id;
    }

    @PostMapping("/orders/{id}/advance")
    public String advanceOrderStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderService.advanceOrderStatus(id);
            redirectAttributes.addFlashAttribute("successMessage", "Order status updated successfully.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/orders/" + id;
    }
}