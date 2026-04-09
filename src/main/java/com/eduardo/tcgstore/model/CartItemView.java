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
/**
 * Represents a temporary cart item stored in session.
 *
 * This is not persisted in the database.
 * It is used to simulate a shopping cart before checkout.
 *
 * It contains product information, quantity, unit price and subtotal.
 */
public class CartItemView {

    private Long productId;
    private String productName;
    private String cardGame;
    private String category;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}