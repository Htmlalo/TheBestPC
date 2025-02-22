package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
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
}
