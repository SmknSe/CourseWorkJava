package com.example.barbershop.services;

import org.springframework.http.ResponseEntity;

public interface TableService<T> {
    public ResponseEntity<?> findAll();
    public ResponseEntity<?> readOneEntity(Integer id);
    public ResponseEntity<?> save(T object);
    public void deleteById(Integer id);
}
