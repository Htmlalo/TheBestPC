package com.thebest.thebestpc.mapper;

import com.thebest.thebestpc.model.Address;
import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> Address mapToEntity(T t) {
        return this.modelMapper.map(t, Address.class);
    }

    public <T> T mapToDto(Users users, Class<T> t) {
        return this.modelMapper.map(users, t);
    }
}
