package com.example.barbershop.DTOs;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MasterDTO {
    private int id;
    private String name;
    private Double rate;
    private List<String> services;
}

