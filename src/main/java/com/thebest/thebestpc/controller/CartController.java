package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.cart.CartService;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CartController {

    CartService cartService;
    CartItemService cartItemService;

    @GetMapping("/cart")
    public String cartView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        List<CartItem> cartItems = cartItemService.getCartItems(users.getCart().getId());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum());
        return "view/CartForm";
    }
}
