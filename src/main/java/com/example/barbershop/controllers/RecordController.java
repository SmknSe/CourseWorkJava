package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Record;
import com.example.barbershop.entities.Serv;
import com.example.barbershop.services.EmailService;
import com.example.barbershop.services.RecordService;
import com.example.barbershop.services.ServService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {
    @Autowired
    private final RecordService recordService;
    @Autowired
    private final ServService servService;


    @GetMapping()
    public ResponseEntity<?> findAll(){
        return recordService.findAll();
    }
    @PostMapping()
    public ModelAndView save(@ModelAttribute("record") RecordDTO record, Authentication authentication){
        record.setUserEmail(authentication.getName());
        record.setPrice(servService.findByName(record.getServiceName()).getPrice());
        recordService.save(record);
        return new ModelAndView("UserInterface");
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        recordService.deleteById(id);
    }

}
