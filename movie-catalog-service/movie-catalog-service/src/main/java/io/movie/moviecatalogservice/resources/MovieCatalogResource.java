package io.movie.moviecatalogservice.resources;

import io.movie.moviecatalogservice.models.CatalogItem;
import io.movie.moviecatalogservice.models.Movie;
import io.movie.moviecatalogservice.models.Rating;
import io.movie.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        //get all rated movie

        UserRating ratings= restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);
       return ratings.getUserRating().stream().map(rating ->{
           //for each movie id call movie info service and get details
           Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
           //put them all together

           return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
       }).collect(Collectors.toList());

    }

}
