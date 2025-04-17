package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.cart.CartService;
import com.thebest.thebestpc.service.cartItem.CartItemService;
import com.thebest.thebestpc.service.manage.CartManagerService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    CartManagerService cartManagerService;

    @GetMapping("{id}")
    public String ddetailPage(@PathVariable Long id,
                              @RequestParam(required = false) String success,
                              @RequestParam(required = false) String error,
                              Model model) {
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        model.addAttribute("product" ,productService.findById(id));
        // load data khác...
        return  "view/DetailForm";
    }

    @PostMapping("addCart/{id}")
    public String addCart(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof AnonymousAuthenticationToken) {
                cartItemService.addOrUpdateCartItemToCookie("cart", new CartCookieDto(id, 1));
                redirectAttributes.addAttribute("success", "Thêm vào giỏ hàng thành công");
                return "redirect:/detail/" + id;
            }
            Users users = (Users) authentication.getPrincipal();
            redirectAttributes.addAttribute("success", "Thêm vào giỏ hàng thành công");
            cartManagerService.addProductToCart(users.getId(), id);

            return "redirect:/detail/" + id;
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Thêm vào giỏ hàng không thành công");
            return "redirect:/detail/" + id;
        }
    }
}
