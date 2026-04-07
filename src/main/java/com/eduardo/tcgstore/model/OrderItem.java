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
public class OrderItem {

    @Id
    private Long id;

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal subtotal;
    private BigDecimal unitPrice;
}