package com.thebest.thebestpc.service.product;

import com.thebest.thebestpc.dto.CreateProductDto;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addProduct(Product product) {

        try {
            productRepository.save(product);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: can't add product");
        }
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findTop5ProductCreateAt() {
        return productRepository.getTop5Product();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error: can't find product"));
    }
}
