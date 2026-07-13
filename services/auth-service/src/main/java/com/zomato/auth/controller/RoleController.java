package com.zomato.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody){

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@RequestParam Long id,@RequestBody ){

    }

    @GetMapping
    public ResponseEntity<?> getRoles(){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@RequestParam Long id){

    }

}
