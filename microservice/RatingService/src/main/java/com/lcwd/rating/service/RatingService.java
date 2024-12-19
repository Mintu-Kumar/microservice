package com.lcwd.rating.service;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all rating
    List<Rating> getRating();

    // get all by userID
    List<Rating> getRatingByUserid(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
