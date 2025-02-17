package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.LoginUserDto;
import com.thebest.thebestpc.dto.RegisterUserDto;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.users.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LoginController {

    UsersService usersService;

    @GetMapping("/login")
    public String loginView() {
        return "view/LoginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserDto loginUserDto, Model model, HttpSession session) {
        try {
            Users users = usersService.findUserByEmail(loginUserDto.getEmail());
            usersService.isValidPassword(users.getEmail(), loginUserDto.getPassword());
            session.setAttribute("nameAccount", users.getFullName());
            return "redirect:/banner";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "view/LoginForm";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserDto registerUserDto, Model model) {
        try {
            Users users = usersService.createNewUser(registerUserDto);
            model.addAttribute("success", "Tạo tài khoản thanh cong");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "view/LoginForm";
        }
    }

}
