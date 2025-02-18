package com.thebest.thebestpc.model;


import com.thebest.thebestpc.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    private Users users;

    @Column(nullable = false, columnDefinition = "nvarchar(63)")
    private String street;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String ward;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String district;

    @Column(nullable = false, columnDefinition = "nvarchar(31)")
    private String city;

    @Column(columnDefinition = "nvarchar(255)")
    private String note;

    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AddressType type = AddressType.HOME;

}
