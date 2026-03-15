package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.model.Customer;
import com.eduardo.tcgstore.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final  CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        if (customer.getName()==null || customer.getName().isBlank()){
            throw new IllegalArgumentException("Customer name is required");
        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
