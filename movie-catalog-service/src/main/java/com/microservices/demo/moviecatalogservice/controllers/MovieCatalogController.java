package com.microservices.demo.moviecatalogservice.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.microservices.demo.moviecatalogservice.models.CatalogItem;
import com.microservices.demo.moviecatalogservice.models.Movie;
import com.microservices.demo.moviecatalogservice.models.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable Integer userId){
        
        //get all rated movie ids
        List<Rating> ratings = Arrays.asList(
            Rating.builder().movieId(1).rating(1).build(),
            Rating.builder().movieId(2).rating(1).build()
        );

        //for each movie id, call movie info service and get details
        return ratings.stream()
                .map(rating -> {
                    Movie movie = this.restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
                    return CatalogItem.builder()
                        .name(movie.getName())
                        .desc("Test")
                        .rating(rating.getRating())
                        .build();
                })
                .collect(Collectors.toList());



        // //put them all together
        // return Collections.singletonList(
        //     CatalogItem.builder()
        //         .name("Transformers")
        //         .desc("Test")
        //         .rating(4)
        //         .build()
        // );
        
    }


}
