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
@Table("ORDER_ITEM")
/**
 * Represents a single item inside an order.
 *
 * Each order item stores the product reference,
 * quantity purchased, unit price at the moment of purchase,
 * and the calculated subtotal.
 *
 * This design ensures historical consistency even if product prices change.
 */
public class OrderItem {

    @Id
    private Long id;

    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}