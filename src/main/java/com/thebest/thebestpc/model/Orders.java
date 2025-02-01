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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    String id;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    ShippingInfo shippingInfo;

    @ManyToOne
            @JoinColumn(name = "users_id", referencedColumnName = "id")
    Users users;
}
