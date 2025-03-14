package com.thebest.thebestpc.dto;

import com.thebest.thebestpc.enums.OrderStatus;
import com.thebest.thebestpc.model.Orders;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private Date createDate;
    private List<OrderItemDto> orderItems;
    private BigDecimal totalPrice;
    public OrderDto(Orders order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.createDate = order.getCreateDate();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
        this.totalPrice = order.getOrderItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
