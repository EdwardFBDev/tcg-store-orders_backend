package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.Customer;
import com.eduardo.tcgstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service responsible for customer management.
 *
 * Handles creation and retrieval of customers.
 * It also provides a utility method to automatically create
 * a customer from an authenticated user during checkout.
 *
 * This allows linking purchases to users without requiring
 * manual customer creation.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        if (customer == null) {
            throw new BusinessException("Customer data is required");
        }

        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new BusinessException("Customer name is required");
        }

        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new BusinessException("Customer email is required");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found"));
    }

    public Customer findOrCreateCustomerFromUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new BusinessException("Username is required");
        }

        for (Customer customer : getAllCustomers()) {
            if (customer.getEmail() != null && customer.getEmail().equalsIgnoreCase(username + "@tcgstore.local")) {
                return customer;
            }
        }

        Customer newCustomer = new Customer();
        newCustomer.setName(username);
        newCustomer.setEmail(username + "@tcgstore.local");

        return customerRepository.save(newCustomer);
    }
}