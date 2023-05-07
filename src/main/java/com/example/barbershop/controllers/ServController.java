package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.DTOs.ServDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Serv;
import com.example.barbershop.services.ServService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServController {

    @Autowired
    private ServService servService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        servService.deleteById(id);
    }

    @GetMapping("/{name}/masters")
    public List<Master> readList(@PathVariable String name){
        return servService.findByName(name).getMasters();
    }
}
