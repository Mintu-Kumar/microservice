package com.lcwd.hotel.controllers;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')" )
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
     return  ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.createHotel(hotel));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getSingleHotel(hotelId));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')" )
    @GetMapping
    public ResponseEntity<List<Hotel>>  getAllHotelList()
    {
        List<Hotel>  allHotel = this.hotelService.getAll();
        return ResponseEntity.ok(allHotel);
    }

}
