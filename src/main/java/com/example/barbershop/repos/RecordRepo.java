package com.example.barbershop.repos;

import com.example.barbershop.entities.Record;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepo extends JpaRepository<Record,Integer> {
    public List<Record> findAllByDate(Date date);
}
