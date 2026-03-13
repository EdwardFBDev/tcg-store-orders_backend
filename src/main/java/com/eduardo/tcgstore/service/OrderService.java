package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.enums.OrderStatus;
import com.eduardo.tcgstore.model.*;
import com.eduardo.tcgstore.repository.OrderRepository;
import com.eduardo.tcgstore.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService (OrderRepository orderRepository, ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Customer customer) {

        Order order = new Order();
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public void addItemToOrder(Long orderId, Long productId, Integer quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than Zero");
        }

        OrderItem item = new OrderItem(product, quantity);

        order.addItem(item);

        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void confirmOrder (Long orderId) {

        Order order = getOrderById(orderId);

        if (order.getItems().isEmpty()) {

            throw new RuntimeException("Order must contain item");
        }
        order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);
    }
}
