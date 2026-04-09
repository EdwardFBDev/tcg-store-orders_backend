package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Product;
import org.springframework.data.repository.CrudRepository;
/**
 * Repository responsible for product persistence operations.
 *
 * Provides basic CRUD operations for Product entities using
 * Spring Data JDBC. It allows retrieving, saving and updating
 * products in the database.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}