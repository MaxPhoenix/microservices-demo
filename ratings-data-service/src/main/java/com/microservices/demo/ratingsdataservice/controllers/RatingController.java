package com.microservices.demo.ratingsdataservice.controllers;

import java.util.Arrays;
import java.util.List;

import com.microservices.demo.ratingsdataservice.models.Rating;
import com.microservices.demo.ratingsdataservice.models.UserRating;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @GetMapping("/{movieId}")
    public Rating getRatingForMovie(@PathVariable Integer movieId){
        return Rating.builder()
                .movieId(movieId)
                .rating(8)
                .build();
    }
    
    @GetMapping("/users/{userId}")
    public UserRating getRatingForUser(@PathVariable Integer userId){
        List<Rating> ratings = Arrays.asList(
            Rating.builder().movieId(1).rating(1).build(),
            Rating.builder().movieId(2).rating(1).build()
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }
}
