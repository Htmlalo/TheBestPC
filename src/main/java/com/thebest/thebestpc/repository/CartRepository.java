package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUsersId(String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.users.id = :userId")
    void deleteCartByUsersId(@Param("userId") String usersId);

}
