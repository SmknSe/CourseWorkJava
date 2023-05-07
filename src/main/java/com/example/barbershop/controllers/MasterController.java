package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Serv;
import com.example.barbershop.repos.MasterRepo;
import com.example.barbershop.services.MasterService;
import com.example.barbershop.services.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/masters")
@RequiredArgsConstructor
public class MasterController {
    @Autowired
    private final MasterService masterService;
    @GetMapping("/{name}/services")
    public List<Serv> readList(@PathVariable String name){
        return masterService.findByName(name).getServices();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        masterService.deleteById(id);
    }
}
