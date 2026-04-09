package com.eduardo.tcgstore.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("PRODUCT")
/**
 * Represents a product available in the store catalog.
 *
 * A product contains information such as name, card game, category,
 * price, stock and status. It is used both in the public shop
 * and in the admin management module.
 *
 * The status field determines if the product can be purchased.
 */
public class Product {

    @Id
    private Long id;

    private String name;
    private CardGame cardGame;
    private ProductCategory category;
    private BigDecimal price;
    private Integer stock;
    private ProductStatus status;

    public enum CardGame {
        POKEMON,
        MAGIC_THE_GATHERING,
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