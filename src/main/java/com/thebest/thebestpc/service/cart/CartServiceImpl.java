package com.thebest.thebestpc.service.cart;

import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.CartItemRepository;
import com.thebest.thebestpc.repository.CartRepository;
import com.thebest.thebestpc.repository.ProductRepository;
import com.thebest.thebestpc.repository.UsersRepository;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {


    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemService cartItemService;

    public CartServiceImpl(UsersRepository usersRepository, CartRepository cartRepository, ProductRepository productRepository, CartItemService cartService) {
        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemService = cartService;
    }

    @Override
    public void addCart(String userId) {
        if (!this.checkout(userId)) {
            Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            cartRepository.save(Cart.builder().users(users).build());
        }

    }

    @Override
    public void removeCart(String userId) {

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




}
