package com.thebest.thebestpc.service.cart;

public interface CartService {
    void addCart(String userId);

    void removeCart(String userId);

    void clearCart(String userId);

    boolean checkout(String userId);

    void addCartItemToCart(String userId, Long productId);
}
