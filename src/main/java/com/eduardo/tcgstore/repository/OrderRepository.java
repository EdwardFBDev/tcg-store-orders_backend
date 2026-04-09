package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Order;
import org.springframework.data.repository.CrudRepository;
/**
 * Repository responsible for order persistence.
 *
 * Handles storage and retrieval of Order aggregates,
 * including their associated OrderItem collections
 * through Spring Data JDBC.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}