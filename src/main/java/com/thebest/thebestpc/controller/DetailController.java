package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.cart.CartService;
import com.thebest.thebestpc.service.product.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/detail")
public class DetailController {

    private final ProductService productService;
    CartService cartService;

    @GetMapping("{id}")
    public String detailProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "view/DetailForm";
    }

    @PostMapping("addCart/{id}")
    public String addCart(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        cartService.addCart(users.getId());
        cartService.addCartItemToCart(users.getId(), id);
        return "redirect:/cart";
    }
}
