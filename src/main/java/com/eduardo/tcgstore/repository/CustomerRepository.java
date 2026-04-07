package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}