package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final Map<Long, Product> productStorage = new HashMap<>();
    private Long nextId = 1L;

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }

        productStorage.put(product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(productStorage.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    public void deleteById(Long id) {
        productStorage.remove(id);
    }
}