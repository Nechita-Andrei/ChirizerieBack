package com.findork.chiriezerie.service;

import com.findork.chiriezerie.model.daos.ReviewDao;
import java.util.List;

public interface ReviewService {
    
    List<ReviewDao> getReviewsByApartment(Long apartmentId);
    
    List<ReviewDao> getReviewsByUser(Long userId);
    
    ReviewDao save(ReviewDao reviewDao);
}
