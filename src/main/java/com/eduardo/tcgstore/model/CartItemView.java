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
public class CartItemView {

    private Long productId;
    private String productName;
    private String cardGame;
    private String category;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}