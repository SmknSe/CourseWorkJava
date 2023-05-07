package com.example.barbershop.services;

import com.example.barbershop.controllers.AuthController;
import com.example.barbershop.entities.User;
import com.example.barbershop.entities.UserDetailsImpl;
import com.example.barbershop.repos.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private EmailService emailService;
    private User waitConfirmUser;
    private String generatedCode;
    @PostConstruct
    private void init() {
        String adminEmail = "admin@admin";
        User admin = userRepo.findByUsername(adminEmail);
        if (admin == null) {
            admin = new User();
            admin.setUsername(adminEmail);
            admin.setPassword("admin");
            admin.setRole("ADMIN");
            signUpUser(admin,false);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsImpl userApp = new UserDetailsImpl(userRepo.findByUsername(email));
        if (userApp.getUser() == null) {
            throw new UsernameNotFoundException("username " + email + " doesn't exist");
        }
        return userApp;
    }
    public String signUpUser(User user, boolean withConfirm) {
        if (!withConfirm){
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
            return "login";
        }
        boolean exist = userRepo.findByUsername(user.getUsername()) != null;
        if (exist) {
            throw new IllegalStateException("already exists");
        }
        generatedCode = generateConfirmationCode(6);
        emailService.sendEmail(generatedCode,user.getUsername(),"Confirm email");
        waitConfirmUser = user;
        System.out.println(waitConfirmUser);
        return "confirm";
    }
    public  String confirm(String code){
        if (generatedCode.equals(code)){
            String password = waitConfirmUser.getPassword();
            waitConfirmUser.setPassword(passwordEncoder.encode(password));
            userRepo.save(waitConfirmUser);
            System.out.println(waitConfirmUser);
            return "login";
        }
        return "reg";
    }
    public String generateConfirmationCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
