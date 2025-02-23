package com.thebest.thebestpc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "shipping_Info")
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "full_name")
    String fullName;
    boolean gender;
    String phone;
    String city;
    String province;
    String district;
    String ward;
    String address;
    String note;
    double price;
    @OneToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    Orders orders;

    // Getters and setters
}
