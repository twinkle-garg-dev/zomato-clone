package com.zomato.auth.requestDTO;

import com.zomato.auth.enums.AddressType;

public record AddressDTO(String city,String state,String country,String pincode,String deliveryAddress,AddressType type) {
}
