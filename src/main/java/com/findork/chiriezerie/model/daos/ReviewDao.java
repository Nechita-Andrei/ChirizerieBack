package com.findork.chiriezerie.model.daos;

import com.findork.chiriezerie.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDao {

    private Long userId;
    private String name;
    private Long apartmentId;
    private String reviewText;
    private Integer rating;

    public ReviewDao(Review review) {
        this.userId = review.getUser().getId();
        this.name = review.getUser().getName();
        this.apartmentId = review.getApartment().getId();
        this.reviewText = review.getReviewText();
        this.rating = review.getRating();
    }
}
