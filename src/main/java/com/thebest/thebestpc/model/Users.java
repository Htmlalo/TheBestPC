package com.thebest.thebestpc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String username;

    @Column(unique = true)
    String email;
    String password;

    String fullName;
    String phone;
    Boolean gender;
    @OneToMany(mappedBy = "users")
    List<Address> address;

    @ManyToOne
    @JoinColumn(name = "default_address_id")
    private Address defaultAddress;
    @OneToOne(mappedBy = "users")
    Cart cart;

    @OneToMany(mappedBy = "users")
    Set<Orders> orders;

    @Column(name = "is_admin")
    @Builder.Default
    Boolean admin = false;

    @Column(name = "is_enabled")
    @Builder.Default
    Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(admin ? "ROLE_ADMIN" : "ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Getters and setters
}

