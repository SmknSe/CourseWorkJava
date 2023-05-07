package com.example.barbershop.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity
@Data
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
public class Serv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;


    @ManyToMany(mappedBy = "services",cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Master> masters = new ArrayList<>();

}
