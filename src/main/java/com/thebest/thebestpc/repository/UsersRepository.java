package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Users;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Transactional(readOnly = true)
    Optional<Users> findUserByEmail(String email);

    Optional<Users> findByEmail(String email);

    @Transactional(readOnly = true)
    @Override
    @NotNull
    Optional<Users> findById(@NotNull String s);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM Users u  WHERE u.id = ?1",nativeQuery = true)
    Optional<Users> findByIdNotCatch(String s);

    boolean existsByEmail(String s);
}
