package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.ServDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Record;
import com.example.barbershop.entities.Serv;
import com.example.barbershop.entities.User;
import com.example.barbershop.services.MasterService;
import com.example.barbershop.services.RecordService;
import com.example.barbershop.services.ServService;
import com.example.barbershop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MasterService masterService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServService servService;


    @GetMapping
    public String getPage(@ModelAttribute("master")MasterDTO masterDTO, @ModelAttribute("service")ServDTO servDTO){
        return "AdminForm";
    }

    @PostMapping("/masters")
    public ModelAndView saveMaster(@ModelAttribute("master") MasterDTO masterDTO){
        masterService.save(masterDTO);
        return new ModelAndView("UserInterface");
    }

    @GetMapping("/records")
    public String findAllRecords(Model model){
        List<Record> records = recordService.findAllList();
        List<RecordDTO> recordDTOS = new ArrayList<>();
        for (Record r:records){
            RecordDTO new_record = new RecordDTO();
            new_record.setId(r.getId());
            new_record.setUserEmail(r.getUser().getUsername());
            new_record.setDate(r.getDate());
            new_record.setTime(r.getTime().toString());
            new_record.setServiceName(r.getServiceName());
            new_record.setMasterName(r.getMaster().getName());
            new_record.setPrice(r.getPrice());
            recordDTOS.add(new_record);
        }
        model.addAttribute("records",recordDTOS);
        return "records";
    }

    @GetMapping("/users")
    public String findAllUsers(Model model, Authentication authentication){
        List<User> users = userService.findAllList();
        model.addAttribute("users",users);
        return "users";
    }


    @PostMapping("/services")
    public ModelAndView saveServ(@ModelAttribute("service")ServDTO servDTO){
        servService.save(servDTO);
        return new ModelAndView("UserInterface");
    }


}
