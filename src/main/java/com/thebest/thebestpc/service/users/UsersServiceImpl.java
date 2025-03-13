package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.dto.RegisterUserDto;
import com.thebest.thebestpc.mapper.UserMapper;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public Users createNewUser(RegisterUserDto dto) {
        if (usersRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Email:" + dto.getEmail() + " Đã tồn tại");

        Users users = userMapper.mapToEntity(dto);
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        return usersRepository.save(users);
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void isValidPassword(String email, String password) {
        if (!passwordEncoder.matches(password, findUserByEmail(email).getPassword()))
            throw new IllegalArgumentException("Mật khẩu khong chính xác");
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users findById(String userId) {
      return   usersRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateSecurityContext(Users users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                users,
                authentication.getCredentials(),
                authentication.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
