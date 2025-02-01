package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.ProductConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, String> {
}
