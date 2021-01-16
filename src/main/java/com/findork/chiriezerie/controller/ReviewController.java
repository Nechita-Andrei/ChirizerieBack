package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.model.daos.ReviewDao;
import com.findork.chiriezerie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @GetMapping("/apartments/{apartmentId}")
    List<ReviewDao> getReviewsByApartmentId(@PathVariable Long apartmentId) {
        return reviewService.getReviewsByApartment(apartmentId);
    }
    
    @GetMapping("/users/{userId}")
    List<ReviewDao> getUsersByApartmentId(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }
    
    @PostMapping
    ReviewDao save(@RequestBody ReviewDao reviewDao) {
        return reviewService.save(reviewDao);
    }
}
