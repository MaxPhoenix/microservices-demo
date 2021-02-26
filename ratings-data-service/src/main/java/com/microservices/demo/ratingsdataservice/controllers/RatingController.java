package com.microservices.demo.ratingsdataservice.controllers;

import com.microservices.demo.ratingsdataservice.models.Rating;

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
    
}
