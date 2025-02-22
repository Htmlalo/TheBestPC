package com.thebest.thebestpc.service.productConfig;

import com.thebest.thebestpc.model.ProductConfig;

import java.util.List;

public interface ProductConfigService {
    void saveAll(List<ProductConfig> productConfigs);
    List<ProductConfig> findByProductId(Long productId);
}
