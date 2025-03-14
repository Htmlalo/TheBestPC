package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Address;
import com.thebest.thebestpc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUsers(Users users);
}
