package com.thebest.thebestpc.controller;

import com.thebest.thebestpc.dto.LoginUserDto;
import com.thebest.thebestpc.dto.RegisterUserDto;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.users.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.jar.Attributes;

@Slf4j
@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LoginController {

    UsersService usersService;

    @RequestMapping("/login")
    public String loginView(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }


        Object errorAttr = request.getAttribute("error");
        if (errorAttr != null) {
            model.addAttribute("error", errorAttr.toString());
        }

        return "view/LoginForm";
    }


//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginUserDto loginUserDto, Model model, HttpSession session) {
//        try {
//            Users users = usersService.findUserByEmail(loginUserDto.getEmail());
//            usersService.isValidPassword(users.getEmail(), loginUserDto.getPassword());
//            session.setAttribute("nameAccount", users.getFullName());
//            return "redirect:/banner";
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//            return "view/LoginForm";
//        }
//    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterUserDto dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        try {
            if (result.hasErrors()) {
                String errorMessage = result.getAllErrors().getFirst().getDefaultMessage();
                model.addAttribute("error", errorMessage);
               // Trả về trang đăng ký nếu có lỗi
                return "view/LoginForm";
            }
            usersService.createNewUser(dto);
            model.addAttribute("success", "Đăng Ký Thành Công");

            return "view/LoginForm"; // Sau khi đăng ký thành công, chuyển hướng đến trang đăng nhập

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "view/LoginForm";
        }
    }

}
