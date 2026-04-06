package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
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

        if (product == null) {
            throw new BusinessException("Product data is required");
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new BusinessException("Product name is required");
        }

        if (product.getPrice() == null) {
            throw new BusinessException("Product price is required");
        }

        if (product.getPrice().signum() < 0) {
            throw new BusinessException("Product price cannot be negative");
        }

        if (product.getStock() == null){
            throw new BusinessException("Product stock is required");
        }

        if (product.getStock() < 0) {
            throw new BusinessException("Product stock cannot be negative");
        }

        if (product.getCategory() == null) {
            throw new BusinessException("Product category is required");
        }

        if (product.getStatus() == null) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));
    }

    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));

        productRepository.deleteById(existingProduct.getId());
    }
}