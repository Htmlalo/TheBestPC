package com.thebest.thebestpc.controller.login;

import com.thebest.thebestpc.dto.UserCreateDto;
import com.thebest.thebestpc.dto.request.ApiResponse;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.service.users.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
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
        return "view/login/LoginForm";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCreateDto userCreateDto) {

        Users users = usersService.findUserByEmail(userCreateDto.getEmail());
        if (users != null && users.getPassword().equals(userCreateDto.getPassword())) {
            return "redirect:/";
        }
        return "view/HomeForm";
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody UserCreateDto userCreateDto) {
        return usersService.createUserApi(userCreateDto);

    }

    @GetMapping("/user")
    public OAuth2User user(OAuth2AuthenticationToken token) {
        return token.getPrincipal();
    }


}
