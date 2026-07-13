package com.zomato.auth.controller;

import com.zomato.auth.requestDTO.UserLoginRequestDTO;
import com.zomato.auth.requestDTO.UserRegisterRequestDTO;
import com.zomato.auth.responseDTO.UserLoginResponseDTO;
import com.zomato.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO dto){
        service.registerUser(dto);
        return ResponseEntity.ok("USER_REGISTERED");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@RequestBody UserLoginRequestDTO dto){
        return ResponseEntity.ok(service.loginUser(dto));
    }

    @GetMapping("/test")
    public String testAuth(){
        return "Auth Endpoint Working";
    }
}
