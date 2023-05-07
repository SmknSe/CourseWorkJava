package com.example.barbershop.services;

import com.example.barbershop.DTOs.MasterDTO;
import com.example.barbershop.DTOs.ServDTO;
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
public class ServService implements TableService<ServDTO>{

    @Autowired
    private final ServRepo servRepo;

    @Autowired
    private final MasterRepo masterRepo;
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(servRepo.findAll());
    }

    public List<Serv> findAllList() {
        return servRepo.findAll();
    }

    @Override
    public ResponseEntity<?> readOneEntity(Integer id) {
        return ResponseEntity.ok(servRepo.findById(id));
    }

    @Override
    public ResponseEntity<?> save(ServDTO servDTO) {
        Serv existing = servRepo.findByName(servDTO.getName());
        if (existing!=null){
            throw new IllegalStateException("already exist");
        }
        Serv serv = new Serv();
        serv.setName(servDTO.getName());
        serv.setPrice(servDTO.getPrice());
        List<Master> masters = new ArrayList<>();

        if (servDTO.getMasters() != null) {
            for (String master : new HashSet<>(servDTO.getMasters())) {
                if (!master.equals("")){
                    Master exist = masterRepo.findByName(master);
                    if (exist!=null){
                        exist.getServices().add(serv);
                        masters.add(exist);
                    }
                    else {
                        Master m = new Master();
                        m.setName(master);
                        m.getServices().add(serv);
                        masters.add(m);
                        masterRepo.save(m);
                    }
                }
            }
        }

        serv.setMasters(masters);
        for (Master m: serv.getMasters()){
            System.out.println(m.getName());
        }
        return ResponseEntity.ok(servRepo.save(serv));
    }

    @Override
    public void deleteById(Integer id){
        servRepo.deleteById(id);
    }
    public Serv findByName(String name){
        return servRepo.findByName(name);
    }
}
