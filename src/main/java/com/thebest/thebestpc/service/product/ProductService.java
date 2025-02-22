package com.thebest.thebestpc.service.product;

import com.thebest.thebestpc.dto.CreateProductDto;
import com.thebest.thebestpc.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    void addProduct(Product product);

    List<Product> findAllProducts();
    Product findById(Long id);
}
