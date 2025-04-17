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
public class Categories {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String name;

    @OneToMany(mappedBy = "categories")
    Set<Product> products;
}
