package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Override
    public Users saveUsers(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }
}
