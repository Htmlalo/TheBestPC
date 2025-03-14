package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.AddressDto;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.address.AddressService;
import com.thebest.thebestpc.service.users.UsersService;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/account")
public class ProfileController {


    AddressService addressService;
    private final UsersService usersService;

    @GetMapping
    public String profileView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        Users users = (Users) authentication.getPrincipal();


        model.addAttribute("userProfile", users);
        model.addAttribute("addressList", addressService.getAllAddress(users.getId()));
        model.addAttribute("addressDto", new AddressDto());
        return "view/ProfileForm";
    }

    @PostMapping("/add-address")
    public String addAddress(@ModelAttribute AddressDto addressDto) {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addressService.addAddress(addressDto, users.getId());
        return "redirect:/account#address";
    }
}
