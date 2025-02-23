package com.thebest.thebestpc.mapper;

import com.thebest.thebestpc.model.ShippingInfo;
import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShippingMapper {
    private final ModelMapper modelMapper;

    public ShippingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> ShippingInfo mapToEntity(T t) {
        return this.modelMapper.map(t, ShippingInfo.class);
    }

    public <T> T mapToDto(Users users, Class<T> t) {
        return this.modelMapper.map(users, t);
    }
}
