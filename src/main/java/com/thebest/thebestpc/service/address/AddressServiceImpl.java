package com.thebest.thebestpc.service.address;

import com.thebest.thebestpc.dto.AddressDto;
import com.thebest.thebestpc.mapper.AddressMapper;
import com.thebest.thebestpc.model.Address;
import com.thebest.thebestpc.model.Users;
import com.thebest.thebestpc.repository.AddressRepository;
import com.thebest.thebestpc.service.users.UsersServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UsersServiceImpl usersServiceImpl;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, UsersServiceImpl usersServiceImpl, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.usersServiceImpl = usersServiceImpl;
        this.addressMapper = addressMapper;
    }

    @Override
    public void addAddress(AddressDto addressDto, String usersId) {
        Address address = addressMapper.mapToEntity(addressDto);
        this.addAddress(address, usersId);
    }

    @Override
    public void addAddress(Address address, String userId) {
        Users users = usersServiceImpl.findById(userId);
        if (users == null) throw new IllegalArgumentException("Users not found");
        address.setUsers(users);
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress(String userId) {
        Users users = usersServiceImpl.findById(userId);
        if (users == null) throw new IllegalArgumentException("Users not found");

        return addressRepository.findByUsers(users);
    }
}
