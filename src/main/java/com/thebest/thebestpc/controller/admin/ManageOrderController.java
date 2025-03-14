package com.thebest.thebestpc.controller.admin;

import com.thebest.thebestpc.dto.OrderDto;
import com.thebest.thebestpc.enums.OrderStatus;
import com.thebest.thebestpc.service.orders.OrdersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ManageOrderController {

    OrdersService ordersService;

    @GetMapping("/api/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@RequestParam(value = "status", required = false) OrderStatus status) {
        List<OrderDto> orders = ordersService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

}
