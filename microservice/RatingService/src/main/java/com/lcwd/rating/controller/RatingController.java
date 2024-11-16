package com.lcwd.rating.controller;


import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')" )   // this is for restrict to access other than Admin roles for that we 've added @EnableGlobalMethodSecurity int securityConfig file
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<Rating>> getRating(){
        return ResponseEntity.ok(this.ratingService.getRating());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')" )
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(this.ratingService.getRatingByUserid(userId));
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(this.ratingService.getRatingByHotelId(hotelId));
    }



}
