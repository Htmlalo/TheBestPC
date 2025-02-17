package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.dto.RegisterUserDto;
import com.thebest.thebestpc.exception.ResourceNotFoundException;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public Users createNewUser(RegisterUserDto dto) {
        if (usersRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Email:" + dto.getEmail() + " Đã tồn tại");

        Users users = Users.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .build();
        return usersRepository.save(users);
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email:" + email + " Không tìm thấy"));
    }

    @Override
    public void isValidPassword(String email, String password) {
        if (!passwordEncoder.matches(password, findUserByEmail(email).getPassword()))
            throw new IllegalArgumentException("Mật khẩu khong chính xác");
    }
}
