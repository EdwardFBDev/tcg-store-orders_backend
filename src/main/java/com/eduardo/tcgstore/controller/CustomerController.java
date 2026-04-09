package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.model.Customer;
import com.eduardo.tcgstore.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * Controller responsible for customer management.
 *
 * Allows administrators to:
 * - View customers
 * - Create customers manually
 *
 * Customers are mainly used to associate purchases with users.
 */
@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers/list";
    }

    @GetMapping("/customers/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    @PostMapping("/customers")
    public String createCustomer(@Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/form";
        }

        customerService.createCustomer(customer);
        return "redirect:/customers";
    }
}