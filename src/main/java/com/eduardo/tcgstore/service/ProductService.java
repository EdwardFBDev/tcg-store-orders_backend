package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (product.getName() == null || product.getName().isBlank()) {
            throw new BusinessException("Product name is required");
        }

        if (product.getCardGame() == null) {
            throw new BusinessException("Card game is required");
        }

        if (product.getCategory() == null) {
            throw new BusinessException("Product category is required");
        }

        if (product.getPrice() == null) {
            throw new BusinessException("Product price is required");
        }

        if (product.getPrice().signum() < 0) {
            throw new BusinessException("Product price cannot be negative");
        }

        if (product.getStock() == null) {
            throw new BusinessException("Product stock is required");
        }

        if (product.getStock() < 0) {
            throw new BusinessException("Product stock cannot be negative");
        }

        if (product.getStatus() == null) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
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

    public Product updateProduct(Product product) {
        if (product == null || product.getId() == null) {
            throw new BusinessException("Product id is required for update");
        }

        Product existingProduct = getProductById(product.getId());

        existingProduct.setName(product.getName());
        existingProduct.setCardGame(product.getCardGame());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setStatus(product.getStatus());

        return productRepository.save(existingProduct);
    }
}