package com.thebest.thebestpc.dto;

import com.thebest.thebestpc.enums.AddressType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    String city;
    private String district;

    private String street;

    private AddressType type;

    private String ward;


}
