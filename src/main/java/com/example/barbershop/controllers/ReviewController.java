package com.example.barbershop.controllers;

import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.ReviewDTO;
import com.example.barbershop.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    @PostMapping()
    public ModelAndView save(@ModelAttribute("review")ReviewDTO reviewDTO, Authentication authentication){
        reviewDTO.setUserEmail(authentication.getName());
        reviewService.save(reviewDTO);
        return new ModelAndView("UserInterface");
    }
}
