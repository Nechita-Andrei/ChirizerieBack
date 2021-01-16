package com.findork.chiriezerie.service.implementation;

import com.findork.chiriezerie.model.Review;
import com.findork.chiriezerie.model.daos.ReviewDao;
import com.findork.chiriezerie.repository.ApartmentRepository;
import com.findork.chiriezerie.repository.ReviewRepository;
import com.findork.chiriezerie.repository.UserRepository;
import com.findork.chiriezerie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    
    private final ApartmentRepository apartmentRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    
    @Override
    public List<ReviewDao> getReviewsByApartment(Long apartmentId) {
        List<Review> reviews = reviewRepository.getAllByApartmentId(apartmentId);
        return reviews.stream()
                .map(ReviewDao::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDao> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.getAllByUserId(userId);
        return reviews.stream()
                .map(ReviewDao::new)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDao save(ReviewDao reviewDao) {
        Review review = reviewRepository.save(translate(reviewDao));
        return new ReviewDao(review);
    }
    
    private Review translate(ReviewDao reviewDao) {
        return Review.builder()
                .user(userRepository.findById(reviewDao.getUserId()).get())
                .apartment(apartmentRepository.findById(reviewDao.getApartmentId()).get())
                .rating(reviewDao.getRating())
                .reviewText(reviewDao.getReviewText())
                .build();
    }


}
