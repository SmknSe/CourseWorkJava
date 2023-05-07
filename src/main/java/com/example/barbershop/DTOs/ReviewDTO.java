package com.example.barbershop.DTOs;

import lombok.Data;

@Data
public class ReviewDTO {

    private String userEmail;
    private String masterName;
    private String serviceName;
    private Double rate;
    private String reviewText;
}
