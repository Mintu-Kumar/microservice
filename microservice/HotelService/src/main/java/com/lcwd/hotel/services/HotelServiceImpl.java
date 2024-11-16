package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found!!"));
    }
}
