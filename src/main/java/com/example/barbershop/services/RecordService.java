package com.example.barbershop.services;

import com.example.barbershop.DTOs.RecordDTO;
import com.example.barbershop.DTOs.UserDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Record;
import com.example.barbershop.entities.User;
import com.example.barbershop.repos.MasterRepo;
import com.example.barbershop.repos.RecordRepo;
import com.example.barbershop.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Dates;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService implements TableService<RecordDTO> {
    @Autowired
    private final RecordRepo recordRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final MasterRepo masterRepo;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(recordRepo.findAll());
    }

    public List<Record> findAllList() {
        return recordRepo.findAll();
    }

    @Override
    public ResponseEntity<?> readOneEntity(Integer id) {
        return ResponseEntity.ok(recordRepo.findById(id));
    }

    @Override
    public ResponseEntity<?> save(RecordDTO recordDTO) {
        Record record = new Record();
        record.setUser(userRepo.findByUsername(recordDTO.getUserEmail()));
        record.setPrice(recordDTO.getPrice());
        record.setDate(recordDTO.getDate());
        record.setTime(Time.valueOf(LocalTime.parse(recordDTO.getTime())));
        record.setServiceName(recordDTO.getServiceName());
        record.setMaster(masterRepo.findByName(recordDTO.getMasterName()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(record.getDate());
        emailService.sendEmail("Вы записаны на "+record.getServiceName()+" к "+record.getMaster().getName()+" на "+ formattedDate+" на "+record.getTime(), record.getUser().getUsername(), "New Record");
        return ResponseEntity.ok(recordRepo.save(record));
    }

    @Override
    public void deleteById(Integer id) {
        recordRepo.deleteById(id);
    }
}
