package com.cschool.cinema.controller;

import com.cschool.cinema.boundary.dto.MovieDto;
import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieById(Long id){
        return movieService.getMovie(id).get();
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Page<Movie> getAllMovies(){
        return movieService.getAllMovies(Pageable.unpaged());
    }

    @GetMapping("{partOfTitle}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Movie> getMovieByPartOfTitle(String partOfTitle){
        return movieService.findByTitleContaining(partOfTitle,Pageable.unpaged());
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateMovie(Long id,
                            @RequestBody MovieDto movieDto){
        Movie movie = movieDto.createMovieFromDto();
        movieService.updateMovie(id,movie.getTitle(),movie.getCategory(),movie.getLength(),movie.getDescription(),
                movie.getRequiredAge(), movieDto.getPosterFilePath());
    }

    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addMovie(@RequestBody MovieDto movieDto){
       Movie movie = movieDto.createMovieFromDto();
       return movieService.createMovie(movie.getTitle(),movie.getCategory(),movie.getLength(),movie.getDescription(),
               movie.getRequiredAge(),movieDto.getPosterFilePath());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMovie(Long id){
        movieService.removeMovie(id);
    }

}
