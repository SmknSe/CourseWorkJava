package com.example.barbershop.DTOs;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ServDTO {
    private int id;
    private String name;

    private int price;
    private List<String> masters;
}
