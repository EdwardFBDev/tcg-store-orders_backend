package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}