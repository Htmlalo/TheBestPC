package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.dto.RegisterUserDto;
import com.thebest.thebestpc.dto.request.ApiResponse;
import com.thebest.thebestpc.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    Users createNewUser(RegisterUserDto dto);

    Users findUserByEmail(String email);

    void isValidPassword(String email, String password);
}
