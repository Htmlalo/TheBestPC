package com.thebest.thebestpc.model;

import com.thebest.thebestpc.enums.OrderStatus;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;

    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    OrderStatus status = OrderStatus.PENDING;

    @Builder.Default
    Date createDate = new Date();

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    ShippingInfo shippingInfo;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    Set<OrderItems> orderItems;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    Users users;
}
