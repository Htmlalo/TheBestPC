package com.thebest.thebestpc.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShippingInfoDto {
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
}
