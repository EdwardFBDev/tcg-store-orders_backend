package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Customer;
import org.springframework.data.repository.CrudRepository;
/**
 * Repository responsible for customer data management.
 *
 * Provides CRUD operations for Customer entities.
 * Customers are used to associate purchases with users.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}