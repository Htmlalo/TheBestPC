package com.thebest.thebestpc.service.orderItem;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.OrderItems;
import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.repository.OrderItemsRepository;
import com.thebest.thebestpc.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemsRepository orderItemsRepository;

    public OrderItemServiceImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }


    @Override
    public void addOrderItem(Orders orders, Product product, int quantity) {
        orderItemsRepository.save(OrderItems.builder()
                .orders(orders)
                .product(product)
                .quantity(quantity)
                .build());
    }

    @Override
    public void addCartItemToOrdersItem(List<CartItem> cartItems, Orders orders) {
        for (CartItem cartItem : cartItems) {
            OrderItems orderItems = OrderItems.builder()
                    .orders(orders)
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .build();
            orderItemsRepository.save(orderItems);
        }
    }


}
