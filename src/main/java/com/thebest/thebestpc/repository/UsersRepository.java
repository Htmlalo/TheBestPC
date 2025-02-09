package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Users findUserByEmail(String email);


    boolean existsByEmail(String s);
}
