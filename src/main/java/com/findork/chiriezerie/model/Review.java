package com.findork.chiriezerie.model;

import com.findork.chiriezerie.feature.account.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
@Builder
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
    
    private Integer rating;
    
    private String reviewText;
}
