package com.eduardo.tcgstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a store order.
 *
 * This entity is the aggregate root for order persistence in Spring Data JDBC.
 * It stores the customer reference, general order information, total amount,
 * and the list of order items associated with the purchase.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("ORDERS")
public class Order {

    @Id
    private Long id;

    private Long customerId;
    private String customerName;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private BigDecimal total;

    /**
     * Child collection of items stored under this order.
     * ORDER_ID links the parent order and ORDERS_KEY preserves list order.
     */
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDERS_KEY")
    private List<OrderItem> items = new ArrayList<>();

    public enum OrderStatus {
        CREATED,
        CONFIRMED,
        DELIVERED,
        CANCELLED
    }
}