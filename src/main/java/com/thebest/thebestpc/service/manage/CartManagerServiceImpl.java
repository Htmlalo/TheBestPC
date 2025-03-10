package com.thebest.thebestpc.service.manage;

import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.service.Cookie.CookieServiceImpl;
import com.thebest.thebestpc.service.cart.CartServiceImpl;
import com.thebest.thebestpc.service.cartItem.CartItemServiceImpl;
import com.thebest.thebestpc.service.product.ProductServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartManagerServiceImpl implements CartManagerService {


    private final CartServiceImpl cartServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final CartItemServiceImpl cartItemServiceImpl;
    private final CookieServiceImpl cookieServiceImpl;

    public CartManagerServiceImpl(CartServiceImpl cartServiceImpl, ProductServiceImpl productServiceImpl, CartItemServiceImpl cartItemServiceImpl, CookieServiceImpl cookieServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.cartItemServiceImpl = cartItemServiceImpl;
        this.cookieServiceImpl = cookieServiceImpl;
    }

    @Override
    public void addProductToCart(String userId, Long productId) {
        cartServiceImpl.addCart(userId);

        Cart cart = cartServiceImpl.getCart(userId);
        Product product = productServiceImpl.findById(productId);

        CartItem cartItem = cartItemServiceImpl.findByCartIdAndProductId(cart.getId(), product.getId());
        if (cartItem != null) {
            cartItemServiceImpl.updateQuantityCartItem(cart, product);
        } else {
            cartItemServiceImpl.addCartItem(cart, product);
        }
    }




    @Override
    public List<CartItem> mergeCartFromCookie(String key, String userId) {
        List<CartItem> cartItems = cartItemServiceImpl.getCartItems(userId);
        List<CartItem> cartItemListCookie = cartItemServiceImpl.getCartItemsFromCookie(key);
        cartServiceImpl.addCart(userId);
        if (cartItemListCookie.isEmpty()) {
            return cartItems;
        }

        Map<Long, CartItem> cartItemMap = cartItems.stream().
                collect(Collectors.toMap(cartItem -> cartItem.getProduct().getId(), cartItem -> cartItem));
        for (CartItem cartItemCookie : cartItemListCookie) {
            Long productId = cartItemCookie.getProduct().getId();

            cartItemMap.merge(productId, cartItemCookie, (existing, fromCookie) -> {
                existing.setQuantity(existing.getQuantity() + fromCookie.getQuantity());
                return existing;

            });
        }
        Cart cart = cartServiceImpl.getCart(userId);
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemServiceImpl.updateQuantityCartItem(cart, cartItem.getProduct(), cartItem.getQuantity());
        }

        cookieServiceImpl.removeCookie("cart");
        return cartItems;
    }

    @Override
    public void removeProductFromCart() {

    }
    @Override
    public void removeCart(String userId) {
        cartServiceImpl.removeCart(userId);
    }
}
