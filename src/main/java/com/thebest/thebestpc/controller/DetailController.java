package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.service.product.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/detail")
public class DetailController {

    private final ProductService productService;

    @GetMapping("{id}")
    public String detailProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "view/DetailForm";
    }
}
