package com.thebest.thebestpc.service.orders;

import com.thebest.thebestpc.dto.OrderDto;
import com.thebest.thebestpc.enums.OrderStatus;
import com.thebest.thebestpc.model.OrderItems;
import com.thebest.thebestpc.model.Orders;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.OrderItemsRepository;
import com.thebest.thebestpc.repository.OrdersRepository;
import com.thebest.thebestpc.repository.UsersRepository;
import com.thebest.thebestpc.service.users.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final UsersService usersService;

    public OrdersServiceImpl(OrdersRepository ordersRepository, UsersRepository usersRepository, OrderItemsRepository orderItemsRepository, UsersService usersService) {
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.usersService = usersService;
    }

    @Override
    public Orders addOrder(String userId) {
        Users users = usersRepository.findById(userId).orElse(null);
        Orders neworders = Orders.builder().users(users).build();
        Orders orders = ordersRepository.save(neworders);
        if (users != null) {
            users.getOrders().add(orders);
            usersService.updateSecurityContext(users);
        }
        return orders;
    }

    @Override
    public List<OrderDto> getOrdersByStatus(OrderStatus status) {

        if (status == null) return ordersRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
        return ordersRepository.findByStatus(status).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTypeOrder(String orderId, int type) {

    }

    @Override
    public void addOrderItemToOrders(OrderItems orderItems, Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderItems.setOrders(orders);
        orderItemsRepository.save(orderItems);

    }

    @Override
    public Orders findOrderByUserId(String userId) {
        return ordersRepository.findOrdersByUsersId(userId).orElse(null);
    }
}
