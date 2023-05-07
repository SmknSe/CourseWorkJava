package com.example.barbershop.DTOs;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RecordDTO {

    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String time;

    private int price;
    private String userEmail;
    private String masterName;
    private String serviceName;
}
