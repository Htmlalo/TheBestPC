package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Procedure("getTop5ProductCreate")
    List<Product> getTop5Product();
}
