package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.model.Order;
import com.eduardo.tcgstore.model.Product;
import com.eduardo.tcgstore.repository.CustomerRepository;
import com.eduardo.tcgstore.repository.OrderRepository;
import com.eduardo.tcgstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
/**
 * Service responsible for calculating business metrics.
 *
 * Provides aggregated data used in the admin dashboard such as:
 * - Total customers
 * - Total orders
 * - Total revenue
 * - Inventory value
 *
 * This service helps simulate real business insights.
 */
@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public DashboardService(CustomerRepository customerRepository,
                            OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public long getTotalCustomers() {
        return customerRepository.count();
    }

    public long getTotalOrders() {
        return orderRepository.count();
    }

    public BigDecimal getTotalRevenue() {
        BigDecimal total = BigDecimal.ZERO;

        for (Order order : orderRepository.findAll()) {
            if (order.getTotal() != null) {
                total = total.add(order.getTotal());
            }
        }

        return total;
    }

    public BigDecimal getInventoryValue() {
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : productRepository.findAll()) {
            if (product.getPrice() != null && product.getStock() != null) {
                total = total.add(product.getPrice()
                        .multiply(BigDecimal.valueOf(product.getStock())));
            }
        }

        return total;
    }
}