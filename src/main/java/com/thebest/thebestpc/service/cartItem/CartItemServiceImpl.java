package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.repository.CartItemRepository;
import com.thebest.thebestpc.service.Cookie.CookieService;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;
    private final CookieService cookieService;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CookieService cookieService) {
        this.cartItemRepository = cartItemRepository;
        this.cookieService = cookieService;
    }

    public void addCartItem(Cart cart, Product product) {
        CartItem cartItem = this.findByCartIdAndProductId(cart.getId(), product.getId());
        if (cartItem != null) {
            cartItemRepository.updateQuantityCartItem(cart.getId(), product.getId());
        } else {
            cartItemRepository.save(CartItem.builder().cart(cart).product(product).build());
        }

    }

    public void removeCartItem(String userId, Long productId) {

    }

    public void clearCartItems(String userId) {

    }

    public void updateCartItem(String userId, Long productId, int quantity) {
        cartItemRepository.updateQuantityCartItem(userId, productId);
    }

    @Override
    public CartItem findByCartIdAndProductId(String cartId, Long productId) {
        return cartItemRepository.findCartItemByCartAndProduct(cartId, productId).orElse(null);
    }

    @Override
    public void addCartItemToCookie(String key, List<Object> value) {
        cookieService.addCookieJson(key, value);
    }

    @Override
    public List<CartItem> getCartItemsFromCookie(String userId) {
        return null;
    }

    @Override
    public void addOrUpdateCartItemToCookie(String key, CartCookieDto value) {
        List<CartCookieDto> cartCookieDtos = cookieService.getCookiesFromJson(key, CartCookieDto.class);
        if (cartCookieDtos != null) {
            for (CartCookieDto cartItem : cartCookieDtos) {
                if (cartItem.getProductId().equals(value.getProductId())) {
                    cartItem.setQuantity(cartItem.getQuantity() + value.getQuantity());
                    cookieService.addCookieJson(key, cartCookieDtos);
                    return;
                }
            }
            cartCookieDtos.add(value);
            cookieService.addCookieJson(key, cartCookieDtos);
        } else {
            cookieService.addCookieJson(key, List.of(value));
        }

    }
}
