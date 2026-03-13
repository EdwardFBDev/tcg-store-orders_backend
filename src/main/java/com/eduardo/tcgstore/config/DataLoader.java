package com.eduardo.tcgstore.config;

import com.eduardo.tcgstore.enums.CardGame;
import com.eduardo.tcgstore.enums.ProductCategory;
import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.service.ProductService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
//config para el proyecto
public class DataLoader {

    @Bean
    // esto le indica a spring que debe crear y ejecutar ese obj
    public CommandLineRunner loadInitialData(ProductService productService) {
        //CommandLineRunner hace que Spring ejecuta al arrancar la app
        return args -> {

            productService.createProduct(new Product(
                    // productService hace que spring inyecta automaticamente
                    null,
                    "Scarlet & Violet Booster Pack",
                    CardGame.POKEMON,
                    ProductCategory.BOOSTER_PACK,
                    new BigDecimal("4.99"),
                    30,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Paldea Evolved Elite Trainer Box",
                    CardGame.POKEMON,
                    ProductCategory.ELITE_TRAINER_BOX,
                    new BigDecimal("49.99"),
                    10,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Charizard ex Special Collection",
                    CardGame.POKEMON,
                    ProductCategory.SPECIAL_COLLECTION,
                    new BigDecimal("39.99"),
                    8,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Modern Horizons III Play Booster",
                    CardGame.MAGIC_THE_GATHERING,
                    ProductCategory.BOOSTER_PACK,
                    new BigDecimal("8.99"),
                    20,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Commander Starter Deck",
                    CardGame.MAGIC_THE_GATHERING,
                    ProductCategory.PRECONSTRUCTED_DECK,
                    new BigDecimal("24.99"),
                    12,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Lorcana First Chapter Sleeves",
                    CardGame.LORCANA,
                    ProductCategory.SLEEVES,
                    new BigDecimal("11.99"),
                    15,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Lorcana Deck Box",
                    CardGame.LORCANA,
                    ProductCategory.DECK_BOX,
                    new BigDecimal("6.99"),
                    18,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "One Piece OP-05 Booster Pack",
                    CardGame.ONE_PIECE,
                    ProductCategory.BOOSTER_PACK,
                    new BigDecimal("5.49"),
                    25,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "One Piece Starter Deck Luffy",
                    CardGame.ONE_PIECE,
                    ProductCategory.PRECONSTRUCTED_DECK,
                    new BigDecimal("17.99"),
                    14,
                    true
            ));

            productService.createProduct(new Product(
                    null,
                    "Pokemon 9-Pocket Binder",
                    CardGame.POKEMON,
                    ProductCategory.BINDER,
                    new BigDecimal("19.99"),
                    9,
                    true
            ));
        };
    }
}
