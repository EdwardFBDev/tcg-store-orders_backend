package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.*;
import com.eduardo.tcgstore.repository.OrderRepository;
import com.eduardo.tcgstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

        if (customer == null) {
            throw new BusinessException("Customer is required");
        }

        if (customer.getId() == null) {
            throw new BusinessException("Customer id is required");
        }

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setCustomerName(customer.getName());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.CREATED);
        order.setTotal(BigDecimal.ZERO);

        return orderRepository.save(order);
    }

    public void addItemToOrder(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("Product not found"));

        if (order.getStatus() != Order.OrderStatus.CREATED) {
            throw new BusinessException("Items can only be added to orders in CREATED status");
        }

        if (product.getStatus() != Product.ProductStatus.ACTIVE) {
            throw new BusinessException("Inactive products cannot be added to an order");
        }

        if (quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero");
        }

        if (product.getStock() < quantity) {
            throw new BusinessException("Not enough stock available for product: " + product.getName());
        }

        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        OrderItem item = new OrderItem(product, quantity, subtotal);

        order.getItems().add(item);
        recalculateOrderTotal(order);

        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException("Order not found"));
    }

    public void confirmOrder(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() != Order.OrderStatus.CREATED) {
            throw new BusinessException("Only orders in CREATED status can be confirmed");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new BusinessException("Order must contain at least one item");
        }

        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new BusinessException("Insufficient stock for product: " + product.getName());
            }
        }

        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        recalculateOrderTotal(order);
        order.setStatus(Order.OrderStatus.CONFIRMED);

        orderRepository.save(order);
    }

    public void advanceOrderStatus(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == Order.OrderStatus.CREATED) {
            confirmOrder(orderId);
            return;
        }

        if (order.getStatus() == Order.OrderStatus.CONFIRMED) {
            order.setStatus(Order.OrderStatus.DELIVERED);
            orderRepository.save(order);
            return;
        }

        throw new BusinessException("Order cannot advance from current status");
    }

    private void recalculateOrderTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                if (item.getSubtotal() != null) {
                    total = total.add(item.getSubtotal());
                }
            }
        }

        order.setTotal(total);
    }
}
