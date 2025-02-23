package com.thebest.thebestpc.service.orderItem;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.OrderItems;
import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.Product;

import java.util.List;

public interface OrderItemService {
    void addOrderItem(Orders orders, Product product, int quantity);

    void addCartItemToOrdersItem(List<CartItem> cartItems, Orders orders);
}
