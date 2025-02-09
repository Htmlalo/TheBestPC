package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.dto.UserCreateDto;
import com.thebest.thebestpc.dto.request.ApiResponse;
import com.thebest.thebestpc.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    ResponseEntity<ApiResponse<Users>> createUserApi(UserCreateDto dto);

    Users findUserByEmail(String email);
}
