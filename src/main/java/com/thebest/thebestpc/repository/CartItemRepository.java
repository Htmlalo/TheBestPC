package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Procedure("UpdateQuantityCartItem")
    void updateQuantityCartItem(@Param("CartId") String CartId, @Param("ProductId") Long ProductId, @Param("@Quantity") Integer quantity);

    @Query(value = "SELECT * FROM cart_items WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
    Optional<CartItem> findCartItemByCartAndProduct(@Param("cartId") String cartId, @Param("productId") Long productId);

    List<CartItem> findAllByCartId(String cartId);

    void deleteAllByCartId(String cartId);
}
