package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;

public interface CartItemService {
    void addCartItem(Cart cart, Product product);

    void removeCartItem(String userId, Long productId);

    void clearCartItems(String userId);

    void updateCartItem(String userId, Long productId, int quantity);

    CartItem findByCartIdAndProductId(String cartId, Long productId);
}
