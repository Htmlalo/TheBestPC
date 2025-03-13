package com.thebest.thebestpc.service.cart;


import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.CartRepository;
import com.thebest.thebestpc.repository.ProductRepository;
import com.thebest.thebestpc.repository.UsersRepository;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import com.thebest.thebestpc.service.cartItem.CartItemServiceImpl;
import com.thebest.thebestpc.service.users.UsersService;

import com.thebest.thebestpc.service.users.UsersServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {


    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final UsersService usersService;
    private final UsersServiceImpl usersServiceImpl;


    public CartServiceImpl(UsersRepository usersRepository, CartRepository cartRepository, UsersService usersService, UsersServiceImpl usersServiceImpl) {
        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository;
        this.usersService = usersService;
        this.usersServiceImpl = usersServiceImpl;
    }

    @Override
    public void addCart(String userId) {


        Users users = usersServiceImpl.findById(userId);
        if (users == null) throw new IllegalArgumentException("User not found");
        Cart cart = Cart.builder().users(users).build();
        if (users.getCart() == null) {
            cartRepository.save(cart);
            users.setCart(cart);
        }
        usersService.updateSecurityContext(users);
    }

    @Override
    @Transactional
    public void removeCart(String userId) {

        Cart cart = findByUsersId(userId);
        if (cart == null) throw new IllegalArgumentException("Tài khoản chua có giỏ hàng không thể xóa ");
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
    public Cart getCart(String userId) {
        return cartRepository.findByUsersId(userId).orElse(null);
    }

    @Override
    public Cart findByUsersId(String userId) {
        return cartRepository.findByUsersId(userId).orElse(null);
    }


}
