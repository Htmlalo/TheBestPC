package com.thebest.thebestpc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true)
    String email;
    String password;

    String fullName;
    String phone;
    Boolean gender;
    String address;


    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    Set<Orders> orders;
    // Getters and setters
}

