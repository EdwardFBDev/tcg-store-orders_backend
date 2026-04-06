package com.eduardo.tcgstore.repository;

import com.eduardo.tcgstore.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final Map<Long, Order> orderStorage = new HashMap<>();
    private Long nextId = 1L;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }

        orderStorage.put(order.getId(), order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderStorage.values());
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderStorage.get(id));
    }

    public void deleteById(Long id) {
        orderStorage.remove(id);
    }
}