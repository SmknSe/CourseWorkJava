package com.example.barbershop.repos;

import com.example.barbershop.entities.Master;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterRepo extends JpaRepository<Master,Integer> {
    public Master findByName(String name);
}
