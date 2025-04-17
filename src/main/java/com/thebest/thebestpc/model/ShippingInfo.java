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

    @Column(name = "full_name" ,nullable = false,columnDefinition = "NVARCHAR(225)")
    String fullName;
    boolean gender;
    String phone;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String city;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String province;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String district;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String ward;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String address;
    @Column(nullable = false, columnDefinition = "nvarchar(225)")
    String note;
    double price;
    @OneToOne
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    Orders orders;

    // Getters and setters
}
