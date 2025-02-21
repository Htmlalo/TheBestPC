package com.thebest.thebestpc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_config")
public class ProductConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "[key]")
    String key;

    String value;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
