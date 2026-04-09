package com.eduardo.tcgstore.service;

import com.eduardo.tcgstore.exception.BusinessException;
import com.eduardo.tcgstore.model.CartItemView;
import com.eduardo.tcgstore.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    @SuppressWarnings("unchecked")
    private Map<Long, Integer> getCart(HttpSession session) {
        Object existingCart = session.getAttribute(CART_SESSION_KEY);

        if (existingCart == null) {
            Map<Long, Integer> newCart = new LinkedHashMap<>();
            session.setAttribute(CART_SESSION_KEY, newCart);
            return newCart;
        }

        return (Map<Long, Integer>) existingCart;
    }

    public void addProduct(HttpSession session, Long productId, int quantity) {
        if (productId == null) {
            throw new BusinessException("Product id is required");
        }

        if (quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero");
        }

        Product product = productService.getProductById(productId);

        if (product.getStatus() != Product.ProductStatus.ACTIVE) {
            throw new BusinessException("Only active products can be added to the cart");
        }

        Map<Long, Integer> cart = getCart(session);
        int existingQuantity = cart.getOrDefault(productId, 0);
        int newQuantity = existingQuantity + quantity;

        if (newQuantity > product.getStock()) {
            throw new BusinessException("Not enough stock available for product: " + product.getName());
        }

        cart.put(productId, newQuantity);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeProduct(HttpSession session, Long productId) {
        Map<Long, Integer> cart = getCart(session);
        cart.remove(productId);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    public List<CartItemView> getCartItems(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        List<CartItemView> items = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            Integer quantity = entry.getValue();
            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));

            items.add(new CartItemView(
                    product.getId(),
                    product.getName(),
                    product.getCardGame().name(),
                    product.getCategory().name(),
                    product.getPrice(),
                    quantity,
                    subtotal
            ));
        }

        return items;
    }

    public int getCartItemCount(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }

    public BigDecimal getCartTotal(HttpSession session) {
        return getCartItems(session).stream()
                .map(CartItemView::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}