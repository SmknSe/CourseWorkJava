package com.example.barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "records")
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Time time;

    @Column(name = "serviceName")
    private String serviceName;

    @Column(name = "price")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Master master;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
