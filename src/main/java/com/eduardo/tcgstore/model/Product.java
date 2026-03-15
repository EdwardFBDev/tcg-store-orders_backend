package com.eduardo.tcgstore.model;

import com.eduardo.tcgstore.enums.CardGame;
import com.eduardo.tcgstore.enums.ProductCategory;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private CardGame cardGame;
    private ProductCategory category;
    private BigDecimal price;
    private int stock;
    private boolean active;
}
