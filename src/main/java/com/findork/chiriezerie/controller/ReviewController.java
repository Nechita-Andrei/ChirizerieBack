package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.model.daos.ReviewDao;
import com.findork.chiriezerie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @GetMapping("/apartments/{apartmentId}")
    List<ReviewDao> getReviewsByApartmentId(@PathVariable Long apartmentId) {
        log.info("Getting reviews by apartmentId={}", apartmentId);
        return reviewService.getReviewsByApartment(apartmentId);
    }
    
    @GetMapping("/users/{userId}")
    List<ReviewDao> getReviewsByUserId(@PathVariable Long userId) {
        log.info("Getting reviews by userId={}", userId);
        return reviewService.getReviewsByUser(userId);
    }
    
    @PostMapping
    ReviewDao save(@RequestBody ReviewDao reviewDao) {
        log.info("Saving review");
        return reviewService.save(reviewDao);
    }
}
