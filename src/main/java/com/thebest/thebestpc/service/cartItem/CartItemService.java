package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CartItemService {
    void addCartItem(Cart cart, Product product);

    void addCartItem(Cart cart, Product product, int quantity);

    void removeCartItem(String userId, Long productId);

    void removeAllCartItem(String cartId);


    void updateQuantityCartItem(Cart cart, Product product);

    void updateQuantityCartItem(Cart cart, Product product, int Quantity);

    List<CartItem> getCartItems(String cartId);

    CartItem findByCartIdAndProductId(String cartId, Long productId);

    void addCartItemToCookie(String key, List<Object> value);

    List<CartItem> getCartItemsFromCookie(String key);

    void addOrUpdateCartItemToCookie(String key, CartCookieDto value);

}
