package com.example.barbershop.services;

import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.UserDTO;
import com.example.barbershop.entities.Record;
import com.example.barbershop.entities.User;
import com.example.barbershop.repos.RecordRepo;
import com.example.barbershop.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements TableService<UserDTO>{
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RecordRepo recordRepo;
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    public List<User> findAllList() {
        return userRepo.findAll();
    }

    public User findByEmail(String email){
        return userRepo.findByUsername(email);
    }

    @Override
    public ResponseEntity<?> readOneEntity(Integer id) {
        return ResponseEntity.ok(userRepo.findById(id));
    }

    @Override
    public ResponseEntity<?> save(UserDTO userDTO) {
            User user = new User();
            User exist = userRepo.findByUsername(user.getUsername());
            if (exist!=null){
                return null;
            }
            user.setUsername(userDTO.getUsername().toLowerCase());
            user.setPassword(userDTO.getPassword());
            return ResponseEntity.ok(userRepo.save(user));
    }

    @Override
    public void deleteById(Integer id){
        userRepo.deleteById(id);
    }

}
