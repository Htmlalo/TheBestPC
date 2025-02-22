package com.thebest.thebestpc.service.productConfig;

import com.thebest.thebestpc.model.ProductConfig;
import com.thebest.thebestpc.repository.ProductConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductConfigServiceImpl implements ProductConfigService {
    private final ProductConfigRepository productConfigRepository;

    public ProductConfigServiceImpl(ProductConfigRepository productConfigRepository) {
        this.productConfigRepository = productConfigRepository;
    }

    @Override
    public void saveAll(List<ProductConfig> productConfigs) {
        productConfigRepository.saveAll(productConfigs);
    }

    @Override
    public List<ProductConfig> findByProductId(Long productId) {
       return productConfigRepository.findConfigsByProductId(productId);
    }

}
