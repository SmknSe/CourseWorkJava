package com.example.barbershop.repos;

import com.example.barbershop.entities.Serv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServRepo extends JpaRepository<Serv,Integer> {
    public Serv findByName(String name);
}
