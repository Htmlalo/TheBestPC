package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.users.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProfileController {


    private final UsersService usersService;

    @GetMapping("/account")
    public String profileView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = null;
        if (authentication.getPrincipal() instanceof Users users1) {
            users = usersService.findUserByEmail(users1.getEmail());
        }
        model.addAttribute("userProfile", users);
        return "view/ProfileForm";
    }
}
