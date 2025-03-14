package com.thebest.thebestpc.dto;

import com.thebest.thebestpc.model.OrderItems;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter

public class OrderItemDto {
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String imageUrl;

    public OrderItemDto(OrderItems item) {
        this.productName = item.getProduct().getName();
        this.quantity = item.getQuantity();
        this.price = item.getProduct().getPrice();
        this.imageUrl = item.getProduct().getImage();
    }
}
