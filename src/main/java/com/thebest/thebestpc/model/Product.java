package com.thebest.thebestpc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
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

    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String name;

    @Column(nullable = false, columnDefinition = "nvarchar(max)")
    String description;

    BigDecimal price;

    String image;

    int stock;

    @Builder.Default
    Date createAt = new Date();

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
