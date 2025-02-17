package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findUserByEmail(String email);


    boolean existsByEmail(String s);
}
