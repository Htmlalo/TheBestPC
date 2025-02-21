package com.thebest.thebestpc.mapper;

import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> Users mapToUser(T t) {
        return this.modelMapper.map(t, Users.class);
    }

    public <T> T mapToDto(Users users, Class<T> t) {
        return this.modelMapper.map(users, t);
    }
}
