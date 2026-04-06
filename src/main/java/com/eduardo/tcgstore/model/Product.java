package com.eduardo.tcgstore.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private CardGame cardGame;
    private ProductCategory category;
    private BigDecimal price;
    private Integer stock;
    private ProductStatus status;


    public enum ProductCategory {
        POKEMON,
        MAGIC,
        LORCANA,
        ONE_PIECE
    }

    public enum ProductCategory {
        BOOSTER_PACK,
        ELITE_TRAINER_BOX,
        SPECIAL_COLLECTION,
        PRECONSTRUCTED_DECK,
        SLEEVES,
        DECK_BOX,
        BINDER
    }

    public enum ProductStatus {
        ACTIVE,
        INACTIVE
    }
}
