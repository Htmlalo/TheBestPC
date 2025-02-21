package com.thebest.thebestpc.mapper;

import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> Product mapToEntity(T t) {
        return this.modelMapper.map(t, Product.class);
    }

    public <T> T mapToDto(Users users, Class<T> t) {
        return this.modelMapper.map(users, t);
    }
}
