package com.thebest.thebestpc.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    String description;

    String price;

    String image;

    int stock;

    @ManyToOne
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    Categories categories;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    Set<OderItem> oderItems;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.PERSIST)
    Set<ProductConfig> productConfigs;
}
