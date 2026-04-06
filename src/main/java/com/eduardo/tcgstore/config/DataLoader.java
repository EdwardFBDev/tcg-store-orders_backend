package com.eduardo.tcgstore.config;

import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadInitialData(ProductService productService) {
        return args -> {
            productService.createProduct(new Product(
                    null,
                    "Scarlet & Violet Booster Pack",
                    Product.CardGame.POKEMON,
                    Product.ProductCategory.BOOSTER_PACK,
                    new BigDecimal("4.99"),
                    30,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Paldea Evolved Elite Trainer Box",
                    Product.CardGame.POKEMON,
                    Product.ProductCategory.ELITE_TRAINER_BOX,
                    new BigDecimal("49.99"),
                    10,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Charizard ex Special Collection",
                    Product.CardGame.POKEMON,
                    Product.ProductCategory.SPECIAL_COLLECTION,
                    new BigDecimal("39.99"),
                    8,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Modern Horizons III Play Booster",
                    Product.CardGame.MAGIC_THE_GATHERING,
                    Product.ProductCategory.BOOSTER_PACK,
                    new BigDecimal("8.99"),
                    20,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Commander Starter Deck",
                    Product.CardGame.MAGIC_THE_GATHERING,
                    Product.ProductCategory.PRECONSTRUCTED_DECK,
                    new BigDecimal("24.99"),
                    12,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Lorcana First Chapter Sleeves",
                    Product.CardGame.LORCANA,
                    Product.ProductCategory.SLEEVES,
                    new BigDecimal("11.99"),
                    15,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Lorcana Deck Box",
                    Product.CardGame.LORCANA,
                    Product.ProductCategory.DECK_BOX,
                    new BigDecimal("6.99"),
                    18,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "One Piece OP-05 Booster Pack",
                    Product.CardGame.ONE_PIECE,
                    Product.ProductCategory.BOOSTER_PACK,
                    new BigDecimal("5.49"),
                    25,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "One Piece Starter Deck Luffy",
                    Product.CardGame.ONE_PIECE,
                    Product.ProductCategory.PRECONSTRUCTED_DECK,
                    new BigDecimal("17.99"),
                    14,
                    Product.ProductStatus.ACTIVE
            ));

            productService.createProduct(new Product(
                    null,
                    "Pokemon 9-Pocket Binder",
                    Product.CardGame.POKEMON,
                    Product.ProductCategory.BINDER,
                    new BigDecimal("19.99"),
                    9,
                    Product.ProductStatus.ACTIVE
            ));
        };
    }
}