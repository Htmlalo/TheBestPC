package com.thebest.thebestpc.service.manage;

import com.thebest.thebestpc.model.CartItem;

import java.util.List;

public interface CartManagerService {


    void addProductToCart(String userId, Long productId);



    List<CartItem> mergeCartFromCookie(String key, String userId);

    void removeProductFromCart();

    void removeCart(String userId);

}
