package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.dto.UserCreateDto;
import com.thebest.thebestpc.dto.request.ApiResponse;
import com.thebest.thebestpc.exception.AppException;
import com.thebest.thebestpc.exception.ErrorMessage;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponse<Users>> createUserApi(UserCreateDto userCreateDto) {

        if (!usersRepository.existsByEmail(userCreateDto.getEmail())) {
            Users users = Users.builder()
                    .fullName(userCreateDto.getFullName())
                    .password(passwordEncoder.encode(userCreateDto.getPassword()))
                    .email(userCreateDto.getEmail())
                    .build();
            return ResponseEntity.ok(ApiResponse.ok(usersRepository.save(users)));
        } else {
            throw new AppException(ErrorMessage.USER_EXIST);
        }
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }
}
