package com.microservices.demo.moviecatalogservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.microservices.demo.moviecatalogservice.models.CatalogItem;
import com.microservices.demo.moviecatalogservice.models.Movie;
import com.microservices.demo.moviecatalogservice.models.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;
    
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable Integer userId){
        
        //get all rated movie ids
        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratings/users/"+userId, UserRating.class);

        //for each movie id, call movie info service and get details
        return userRating.getRatings()
                .stream()
                .map(rating -> {
                    Movie movie = this.restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
                    
                     //put them all together
                    return CatalogItem.builder()
                        .name(movie.getName())
                        .desc("Test")
                        .rating(rating.getRating())
                        .build();
                })
                .collect(Collectors.toList());


        
       //Alternative using WebCLient (webflux)
            /*  Movie movie = this.webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/movies/"+rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
               .block(); */
        
    }


}
