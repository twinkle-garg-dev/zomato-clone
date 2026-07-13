package com.zomato.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {


    @PostMapping("/{id}")
    public void updateProfile(@RequestBody ){

    }
}
