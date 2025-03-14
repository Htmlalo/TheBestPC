package com.thebest.thebestpc.service.orders;

import com.thebest.thebestpc.dto.OrderDto;
import com.thebest.thebestpc.enums.OrderStatus;
import com.thebest.thebestpc.model.OrderItems;
import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.Product;

import java.util.List;

public interface OrdersService {
    Orders addOrder(String userId);

    List<OrderDto> getOrdersByStatus(OrderStatus status);


    void updateTypeOrder(String orderId, int type);

    void addOrderItemToOrders(OrderItems orderItems, Long orderId);

    Orders findOrderByUserId(String userId);
}
