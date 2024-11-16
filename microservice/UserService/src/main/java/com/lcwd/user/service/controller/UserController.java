package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        System.out.println("Request body="+user);
       User user1 = this.userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name="serviceHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getUser(@PathVariable String userId){

        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex)
    {
        ex.printStackTrace();
        System.out.println("This fallback is calling because service is down"+ ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because service is down")
                .userId("3243243")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
       List<User>  allUser =  this.userService.getAllUser();
       System.out.println("mintu"+allUser);
       return ResponseEntity.ok(allUser);

    }

}
