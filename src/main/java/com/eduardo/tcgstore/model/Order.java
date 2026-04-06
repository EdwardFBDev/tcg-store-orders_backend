package com.eduardo.tcgstore.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Long customerId;
    private String customerName;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal total;

    public enum OrderStatus {
        CREATED,
        CONFIRMED,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

}
