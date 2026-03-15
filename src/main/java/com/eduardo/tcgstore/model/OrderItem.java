package com.eduardo.tcgstore.model;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderItem {

    private Product product;
    private int quantity;

    public BigDecimal getSubtotal(){

        if (product == null || product.getPrice() == null){
            return BigDecimal.ZERO;
        }
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
