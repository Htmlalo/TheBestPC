package com.thebest.thebestpc.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
public class RegisterUserDto {
    String email;
    String password;
    String fullName;
}
