package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}