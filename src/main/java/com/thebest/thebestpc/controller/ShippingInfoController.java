package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.ShippingInfoDto;
import com.thebest.thebestpc.mapper.ShippingMapper;
import com.thebest.thebestpc.model.*;
import com.thebest.thebestpc.service.cart.CartService;
import com.thebest.thebestpc.service.orderItem.OrderItemService;
import com.thebest.thebestpc.service.orders.OrdersService;
import com.thebest.thebestpc.service.shippingInfo.ShippingInfoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ShippingInfoController {
    OrdersService ordersService;
    OrderItemService orderItemService;
    ShippingInfoService shippingInfoService;
    private final ShippingMapper shippingMapper;
    CartService cartService;

    @GetMapping("/shippingInfo")
    public String shippingInfoView(@RequestParam double total, Model model) {
        model.addAttribute("total", total);
        model.addAttribute("shippingInfo", new ShippingInfoDto());
        return "view/ShippingInfoForm";
    }

    @PostMapping("/shippingInfo")
    public String shippingInfoSubmit(@ModelAttribute ShippingInfoDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        Users users = (Users) authentication.getPrincipal();
        ordersService.addOrder(users.getId());
        Cart cart = cartService.findByUsersId(users.getId());
        List<CartItem> cartItems = cart.getCartItems().stream().toList();
        Orders orders = ordersService.findOrderByUserId(users.getId());
        orderItemService.addCartItemToOrdersItem(cartItems, orders);
        ShippingInfo shippingInfo = shippingMapper.mapToEntity(dto);
        shippingInfoService.addShippingInfoToOrders(shippingInfo, orders);
        cartService.removeCart(users.getId());
        return "view/ShippingInfoForm";
    }
}

