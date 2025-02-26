package com.thebest.thebestpc.mapper;

import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartItemsMapper {
    private final ModelMapper modelMapper;

    public CartItemsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> CartItem mapToEntity(T t) {
        return this.modelMapper.map(t, CartItem.class);
    }

    public <T> T mapToDto(CartItem entity, Class<T> t) {
        return this.modelMapper.map(entity, t);
    }
}
