package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    // Dependency injection...
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {

        if (product.getName() == null || product.getName().isBlank()){
            throw new IllegalArgumentException("Product name is required");
        }

        if (product.getPrice() == null){
            throw new IllegalArgumentException("Product price is required");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
