package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.ProductConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, String> {
    @Query("SELECT pc FROM ProductConfig pc WHERE pc.product.id = :productId")
    List<ProductConfig> findConfigsByProductId(@Param("productId") Long productId);

}
