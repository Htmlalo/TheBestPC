package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.service.product.ProductService;
import com.thebest.thebestpc.service.users.UsersService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Home {

    ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "view/HomeForm";
    }
}
