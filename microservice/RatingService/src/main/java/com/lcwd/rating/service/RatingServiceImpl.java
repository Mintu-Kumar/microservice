package com.lcwd.rating.service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements  RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserid(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
