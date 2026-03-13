package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Customer;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {

    private Map<Long, Customer> customerStorage = new HashMap<>();
    private Long nextId = 1L;


    public Customer save(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(nextId++);
        }

        customerStorage.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerStorage.values());
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerStorage.get(id));
    }

    public void deleteById(Long id) {
        customerStorage.remove(id);
    }

}