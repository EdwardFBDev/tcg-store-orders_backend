package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {

        if (product.getPrice() != null && product.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}