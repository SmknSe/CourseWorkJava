package com.example.barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "text")
    private String text;

    @Column(name = "service")
    private String serviceName;

    @Column(name = "userEmail")
    private String userEmail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Master master;
}
