package com.thebest.thebestpc.service.cart;


import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.CartRepository;
import com.thebest.thebestpc.repository.ProductRepository;
import com.thebest.thebestpc.repository.UsersRepository;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import com.thebest.thebestpc.service.users.UsersService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {


    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemService cartItemService;
    private final UsersService usersService;


    public CartServiceImpl(UsersRepository usersRepository, CartRepository cartRepository, ProductRepository productRepository, CartItemService cartService, UsersService usersService) {
        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemService = cartService;
        this.usersService = usersService;
    }

    @Override
    public void addCart(String userId) {
        if (!this.checkout(userId)) {
            Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Cart cart = Cart.builder().users(users).build();
            cartRepository.save(cart);
            users.setCart(cart);
            usersService.updateSecurityContext(users);
        }

    }

    @Override
    @Transactional
    public void removeCart(String userId) {
        Cart cart = findByUsersId(userId);
        cartRepository.delete(cart);
    }

    @Override
    public void clearCart(String userId) {

    }

    @Override
    public boolean checkout(String userId) {
        return cartRepository.findByUsersId(userId).isPresent();
    }

    @Override
    public void addCartItemToCart(String userId, Long productId) {
        Cart cart = cartRepository.findByUsersId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        cartItemService.addCartItem(cart, product);
    }

    @Override
    public Cart getCart(String userId) {
        return cartRepository.findByUsersId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Cart findByUsersId(String userId) {
        return cartRepository.findByUsersId(userId).orElse(null);
    }


}
