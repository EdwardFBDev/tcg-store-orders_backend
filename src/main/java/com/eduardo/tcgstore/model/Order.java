package com.eduardo.tcgstore.model;

import com.eduardo.tcgstore.enums.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private Long id;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;
    private LocalDateTime createdAt = LocalDateTime.now();

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }
}
