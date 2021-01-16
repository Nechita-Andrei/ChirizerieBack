package com.findork.chiriezerie.repository;

import com.findork.chiriezerie.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
    List<Review> getAllByApartmentId(Long apartmentId);
    
    List<Review> getAllByUserId(Long userId);
}
