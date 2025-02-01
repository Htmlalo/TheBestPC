package com.thebest.thebestpc.service.users;

import com.thebest.thebestpc.model.Users;


public interface UsersService {

    Users saveUsers(Users users);

    Users findUserByEmail(String email);
}
