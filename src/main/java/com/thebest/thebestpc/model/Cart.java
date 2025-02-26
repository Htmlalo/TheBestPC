package com.thebest.thebestpc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart")
public class Cart {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String id;

    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    Date createdAt = new Date();

    @OneToMany(mappedBy = "cart",cascade = CascadeType.REMOVE ,orphanRemoval = true)
    Set<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id", nullable = true)
    Users users;

}
