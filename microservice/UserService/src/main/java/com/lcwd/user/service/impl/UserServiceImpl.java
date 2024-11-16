package com.lcwd.user.service.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
       String randomUserId =  UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from the database with the help of database
         User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource with given id is not found on service"+userId));
         //fetch the rating of the above user from the RATING  SERVICE
        //http://localhost:8082/ratings/users/0453d806-05f4-48fe-96ac-3842173f257b
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
        List<Rating>ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel serviceto get the list
            //api = http://localhost:8081/hotels/ae1d8f17-3764-4d54-b6a5-a433027bc176
           ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
           Hotel hotel =  forEntity.getBody();
           logger.info("Response status code :{}",forEntity.getStatusCode());
            //set the hotel to rating
             rating.setHotel(hotel);
            //return the rating
                return rating;
            //return retaing

        }).collect(Collectors.toList());
        logger.info("{}",ratingList);
        user.setRatings(ratingList);
        return user;
    }
}
