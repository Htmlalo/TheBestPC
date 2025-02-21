package com.thebest.thebestpc.dto;

import com.thebest.thebestpc.model.Categories;
import com.thebest.thebestpc.model.ProductConfig;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
public class CreateProductDto {
    String name;
    String description;
    String price;
    String image;
    int stock;
    Categories categories;
    List<ProductConfig> productConfigs;
}
