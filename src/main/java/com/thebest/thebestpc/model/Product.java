package com.thebest.thebestpc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    String description;

    BigDecimal price;

    String image;

    int stock;

    @ManyToOne
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    Categories categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<OrderItems> oderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonIgnore
    Set<ProductConfig> productConfigs;
}
