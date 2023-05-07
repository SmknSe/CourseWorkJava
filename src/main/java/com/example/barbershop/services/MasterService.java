package com.example.barbershop.services;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Serv;
import com.example.barbershop.repos.MasterRepo;
import com.example.barbershop.repos.ServRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterService implements TableService<MasterDTO> {
    @Autowired
    private final MasterRepo masterRepo;
    @Autowired
    private final ServRepo servRepo;
    @Override
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(masterRepo.findAll());
    }
    public List<Master> findAllList() {
        return masterRepo.findAll();
    }
    @Override
    public ResponseEntity<?> readOneEntity(Integer id) {
        return ResponseEntity.ok(masterRepo.findById(id));
    }
    @Override
    public ResponseEntity<?> save(MasterDTO masterDTO) {
        Master existing = masterRepo.findByName(masterDTO.getName());
        if (existing!=null){
            throw new IllegalStateException("already exist");
        }
        Master master = new Master();
        master.setName(masterDTO.getName());
        List<Serv> servs = new ArrayList<>();
        if (masterDTO.getServices()!=null){
            for (String serv : new HashSet<>(masterDTO.getServices())) {
                if (!serv.equals("")){
                    Serv exist = servRepo.findByName(serv);
                    if (exist!=null){
                        servs.add(exist);
                    }
                    else {
                        Serv s = new Serv();
                        s.setName(serv);
                        s.setPrice(1500);
                        servs.add(s);
                    }
                }
            }
        }
        master.setServices(servs);
        return ResponseEntity.ok(masterRepo.save(master));
    }
    public Master findByName(String name){
        return masterRepo.findByName(name);
    }
    @Override
    public void deleteById(Integer id){
        masterRepo.deleteById(id);
    }
}
