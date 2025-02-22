package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.cart.CartService;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import com.thebest.thebestpc.service.product.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/detail")
public class DetailController {

    private final ProductService productService;
    CartService cartService;
    CookieService cookieService;
    CartItemService cartItemService;

    @GetMapping("{id}")
    public String detailProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "view/DetailForm";
    }

    @PostMapping("addCart/{id}")
    public String addCart(@PathVariable Long id) {
        System.out.println(cookieService.getCookiesFromJson("cart", CartCookieDto.class).getFirst());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            cartItemService.addOrUpdateCartItemToCookie("cart", new CartCookieDto(id, 1));
            return "redirect:/detail/" + id;
        }
        Users users = (Users) authentication.getPrincipal();
        cartService.addCart(users.getId());
        cartService.addCartItemToCart(users.getId(), id);

        return "redirect:/detail/" + id;
    }
}
