package com.thebest.thebestpc.service.cart;

import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;

import java.util.List;

public interface CartService {
    void addCart(String userId);

    void removeCart(String userId);

    void clearCart(String userId);

    boolean checkout(String userId);



    Cart getCart(String userId);

    Cart findByUsersId(String userId);


}
