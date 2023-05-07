package com.example.barbershop.controllers;

import com.example.barbershop.entities.User;
import com.example.barbershop.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserDetailService userDetailService;
    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("user") User user){
        return "login";
    }
    @GetMapping("/reg")
    public String getRegPage(@ModelAttribute("user") User user, Model model){
        List<String> usernames = new ArrayList<>();
        for (User u:userDetailService.getUsers()){
            usernames.add(u.getUsername());
        }
        model.addAttribute("usernames",usernames);
        return "reg";
    }
    @PostMapping("/reg")
    public String signUp(@ModelAttribute("user") User user){
        user.setRole("USER");
        return userDetailService.signUpUser(user,true);
    }
    @PostMapping("/confirm")
    public String confirm(@RequestParam("code") String code, @ModelAttribute("user") User user){
        return userDetailService.confirm(code);
    }
}
