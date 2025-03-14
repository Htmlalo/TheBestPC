package com.thebest.thebestpc.service.manage;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.ShippingInfo;
import com.thebest.thebestpc.service.cartItem.CartItemServiceImpl;
import com.thebest.thebestpc.service.orderItem.OrderItemServiceImpl;
import com.thebest.thebestpc.service.orders.OrdersServiceImpl;
import com.thebest.thebestpc.service.shippingInfo.ShippingInfoServiceImpl;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManagerServiceImpl implements OrderManagerService {

    private final OrdersServiceImpl ordersServiceImpl;
    private final OrderItemServiceImpl orderItemServiceImpl;
    private final CartItemServiceImpl cartItemServiceImpl;
    private final ShippingInfoServiceImpl shippingInfoServiceImpl;

    public OrderManagerServiceImpl(OrdersServiceImpl ordersServiceImpl, OrderItemServiceImpl orderItemServiceImpl, CartItemServiceImpl cartItemServiceImpl, ShippingInfoServiceImpl shippingInfoServiceImpl) {
        this.ordersServiceImpl = ordersServiceImpl;
        this.orderItemServiceImpl = orderItemServiceImpl;
        this.cartItemServiceImpl = cartItemServiceImpl;
        this.shippingInfoServiceImpl = shippingInfoServiceImpl;
    }

    @Override
    public void addProductFromOrder(String userId, String cartId, ShippingInfo shippingInfo) {
        Orders orders = ordersServiceImpl.addOrder(userId);

        List<CartItem> cartItems = cartItemServiceImpl.getCartItems(cartId);
        orderItemServiceImpl.addCartItemToOrdersItem(cartItems, orders);
        cartItemServiceImpl.removeAllCartItem(cartId);
        shippingInfoServiceImpl.addShippingInfoToOrders(shippingInfo,orders);
    }

    @Override
    public void addShipInfoFromOrder() {

    }
}
