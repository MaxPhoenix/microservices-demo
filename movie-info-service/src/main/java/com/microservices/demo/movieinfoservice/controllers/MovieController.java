package com.microservices.demo.movieinfoservice.controllers;

import com.microservices.demo.movieinfoservice.models.Movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable Integer movieId){
        return Movie.builder()
            .name("Transformers")
            .movieId(movieId)
            .build();
    }



}
