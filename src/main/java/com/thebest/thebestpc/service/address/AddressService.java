package com.thebest.thebestpc.service.address;

import com.thebest.thebestpc.dto.AddressDto;
import com.thebest.thebestpc.model.Address;

import java.util.List;

public interface AddressService {
    void addAddress(AddressDto addressDto,String userId);

    void addAddress(Address address,String userId);

    List<Address> getAllAddress(String userId);
}
