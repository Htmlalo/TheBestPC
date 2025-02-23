package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;

import java.util.List;

public interface CartItemService {
    void addCartItem(Cart cart, Product product);

    void removeCartItem(String userId, Long productId);

    void clearCartItems(String userId);

    void updateCartItem(String userId, Long productId, int quantity);

    List<CartItem> getCartItems(String cartId);
    CartItem findByCartIdAndProductId(String cartId, Long productId);

    void addCartItemToCookie(String key, List<Object> value);

    List<CartItem> getCartItemsFromCookie(String key);

    void addOrUpdateCartItemToCookie(String key, CartCookieDto value);
}
