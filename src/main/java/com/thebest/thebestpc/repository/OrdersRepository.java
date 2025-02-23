package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findOrdersByUsersId(String userId);

    @Modifying
    @Query("UPDATE Orders o SET o.status = :type WHERE o.id = :orderId")
    void updateTypeOrder(@Param("type") Long orderId, @Param("orderId") int type);
}
