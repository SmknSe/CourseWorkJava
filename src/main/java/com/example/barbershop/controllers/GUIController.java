package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.ReviewDTO;
import com.example.barbershop.DTOs.ServDTO;
import com.example.barbershop.entities.*;
import com.example.barbershop.entities.Record;
import com.example.barbershop.services.MasterService;
import com.example.barbershop.services.ReviewService;
import com.example.barbershop.services.ServService;
import com.example.barbershop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GUIController {
    @Autowired
    private MasterService masterService;
    @Autowired
    private ServService servService;

    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/addRecord")
    public String getAddingRecordPage(Model model){
//        model.addAttribute("masters", masterService.findAllList());
        model.addAttribute("services", servService.findAllList());
        model.addAttribute("record", new RecordDTO());
        return "addRecord";
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        return "UserInterface";
    }

    @GetMapping("/myrecords")
    public String findAllRecords(Model model, Authentication authentication){
        User user = userService.findByEmail(authentication.getName());
        List<Record> records = user.getRecords();
        List<RecordDTO> recordDTOS = new ArrayList<>();
        for (Record r:records){
            RecordDTO new_record = new RecordDTO();
            new_record.setId(r.getId());
            new_record.setUserEmail(r.getUser().getUsername());
            new_record.setPrice(r.getPrice());
            new_record.setDate(r.getDate());
            new_record.setTime(r.getTime().toString());
            new_record.setServiceName(r.getServiceName());
            new_record.setMasterName(r.getMaster().getName());
            recordDTOS.add(new_record);
        }
        model.addAttribute("records",recordDTOS);
        return "records";
    }

    @GetMapping("/reviews")
    public String getReviews(Model model){
        List<Review> reviews = reviewService.findAllList();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review r:reviews){
            ReviewDTO new_review = new ReviewDTO();
            new_review.setUserEmail(r.getUserEmail());
            new_review.setReviewText(r.getText());
            new_review.setRate(r.getRate());
            new_review.setMasterName(r.getMaster().getName());
            new_review.setServiceName(r.getServiceName());
            reviewDTOS.add(new_review);
        }
        model.addAttribute("reviews",reviewDTOS);
        return "reviews";
    }

    @GetMapping("/add-review")
    public String getAddReview(Model model){
        model.addAttribute("masters", masterService.findAllList());
        model.addAttribute("services", servService.findAllList());
        model.addAttribute("review", new ReviewDTO());
        return "addReview";
    }

    @GetMapping("/masters")
    public String findAllMasters(Model model, Authentication authentication){
        List<Master> masters = masterService.findAllList();
        List<MasterDTO> masterDTOS = new ArrayList<>();
        for (Master m:masters){
            MasterDTO new_master = new MasterDTO();
            new_master.setId(m.getId());
            new_master.setName(m.getName());
            new_master.setRate(m.getRate());
            List<String> services = new ArrayList<>();
            for (Serv s:m.getServices()){
                services.add(s.getName());
            }
            new_master.setServices(services);
            masterDTOS.add(new_master);
        }
        model.addAttribute("masters",masterDTOS);
        model.addAttribute("role",authentication.getAuthorities());
        return "masters";
    }

    @GetMapping("/services")
    public String findAllServices(Model model, Authentication authentication){
        List<Serv> services =  servService.findAllList();
        List<ServDTO> servDTOS = new ArrayList<>();
        for (Serv s : services){
            ServDTO new_serv = new ServDTO();
            new_serv.setId(s.getId());
            new_serv.setName(s.getName());
            new_serv.setPrice(s.getPrice());
            List<String> masters = new ArrayList<>();
            for(Master m: s.getMasters()){
                masters.add(m.getName());
            }
            new_serv.setMasters(masters);
            servDTOS.add(new_serv);
        }
        model.addAttribute("services",servDTOS);
        model.addAttribute("role",authentication.getAuthorities());
        return "services";
    }

}
