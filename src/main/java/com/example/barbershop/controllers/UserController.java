package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.UserDTO;
import com.example.barbershop.entities.Record;
import com.example.barbershop.entities.User;
import com.example.barbershop.services.RecordService;
import com.example.barbershop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/myrole")
    public String getRole(Authentication authentication){
        User user = userService.findByEmail(authentication.getName());
        return user.getRole();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }
}
