package com.zomato.auth.requestDTO;


import java.util.List;

public record UserRegisterRequestDTO(String firstName, String lastName,String emailId,
                                     String phoneNumber,String username,String password,
                                     Boolean useEmailAsUsername,String role,List<AddressDTO> addresses) {
}

