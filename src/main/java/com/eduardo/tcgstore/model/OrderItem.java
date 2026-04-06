package com.eduardo.tcgstore.model;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {

    private Product product;
    private Integer quantity;
    private BigDecimal subtotal;

}
