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

    public OrderService (OrderRepository orderRepository,
                         ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Customer customer) {

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    public void addItemToOrder(Long orderId, Long productId, int quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new RuntimeException("Items can only be added to orders in CREATED status");
        }

        if (!product.isActive()) {
            throw new RuntimeException("Inactive products cannot be added to an order");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (quantity > product.getStock()) {
            throw new RuntimeException("Not enough stock available for product: " + product.getName());
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

    public void confirmOrder(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new RuntimeException("Only orders in CREATED status can be confirmed");
        }

        if (order.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName()
                );
            }
        }

        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    public void advanceOrderStatus(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.CREATED) {
            confirmOrder(orderId);
            return;
        }

        if (order.getStatus() == OrderStatus.CONFIRMED) {
            order.setStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
            return;
        }

        throw new RuntimeException("Order cannot advance from current status");
    }
}
