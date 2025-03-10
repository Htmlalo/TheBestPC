package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import com.thebest.thebestpc.service.manage.CartManagerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {


    CartItemService cartItemService;
    CookieService cookieService;
    CartManagerService cartManagerService;

    @GetMapping
    public String cartView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {


            if (cookieService.getCookie("cart") != null) {
                model.addAttribute("cartItems", cartItemService.getCartItemsFromCookie("cart"));
                return "view/CartForm";
            }
        }
        Users users = (Users) authentication.getPrincipal();
        List<CartItem> cartItems = cartManagerService.mergeCartFromCookie("cart", users.getId());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum());
        return "view/CartForm";
    }
}
