package com.lcwd.rating.repository;

import com.lcwd.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

    // Below are custom find method and UserId is column name like findBy+Column name
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
