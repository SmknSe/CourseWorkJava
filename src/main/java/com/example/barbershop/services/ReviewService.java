package com.example.barbershop.services;

import com.example.barbershop.DTOs.ReviewDTO;
import com.example.barbershop.entities.Master;
import com.example.barbershop.entities.Review;
import com.example.barbershop.repos.MasterRepo;
import com.example.barbershop.repos.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements TableService<ReviewDTO> {

    @Autowired
    private final ReviewRepo reviewRepo;
    @Autowired
    private final MasterRepo masterRepo;
    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(reviewRepo.findAll());
    }

    @Override
    public ResponseEntity<?> readOneEntity(Integer id) {
        return ResponseEntity.ok(reviewRepo.findById(id));
    }

    @Override
    public ResponseEntity<?> save(ReviewDTO reviewDTO) {
        Master existing = masterRepo.findByName(reviewDTO.getMasterName());
        Review review = new Review();
        review.setRate(reviewDTO.getRate());
        review.setText(reviewDTO.getReviewText());
        review.setServiceName(reviewDTO.getServiceName());
        review.setUserEmail(reviewDTO.getUserEmail());
        existing.getReviews().add(review);
        review.setMaster(existing);
        existing.setRate((existing.getRate()+review.getRate())/existing.getReviews().size());
        return ResponseEntity.ok(reviewRepo.save(review));
    }

    @Override
    public void deleteById(Integer id) {
        reviewRepo.deleteById(id);
    }

    public List<Review> findAllList(){
        return reviewRepo.findAll();
    }
}
