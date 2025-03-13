package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.ShippingInfoDto;
import com.thebest.thebestpc.mapper.ShippingMapper;
import com.thebest.thebestpc.model.*;
import com.thebest.thebestpc.service.manage.OrderManagerService;
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

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ShippingInfoController {
    ShippingMapper shippingMapper;
    OrderManagerService orderManagerService;

    @GetMapping("/shippingInfo")
    public String shippingInfoView(@RequestParam double total, Model model) {
        ShippingInfoDto shippingInfoDto = new ShippingInfoDto();
        shippingInfoDto.setPrice(total);
        model.addAttribute("total", total);
        model.addAttribute("shippingInfo", shippingInfoDto);
        return "view/ShippingInfoForm";
    }

    @PostMapping("/shippingInfo")
    public String shippingInfoSubmit(@ModelAttribute ShippingInfoDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        Users users = (Users) authentication.getPrincipal();
        ShippingInfo shippingInfo = shippingMapper.mapToEntity(dto);
        orderManagerService.addProductFromOrder(users.getId(), users.getCart().getId(), shippingInfo);
        return "view/ShippingInfoForm";
    }
}

