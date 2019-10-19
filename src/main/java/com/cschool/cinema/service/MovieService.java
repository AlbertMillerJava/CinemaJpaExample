package com.cschool.cinema.service;

import com.cschool.cinema.domain.EMovieCategory;
import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieService {

    Long createMovie(String title, EMovieCategory category, int length,
                     String description, int requiredAge,String posterFilePath);

    Page<Movie> findByCategory(EMovieCategory category, Pageable pageable);

    Page<Movie> findByTitleContaining (String partOfTitle, Pageable pageable);

    Optional<Movie> getMovie(Long id);

    Page<Movie> getAllMovies(Pageable pageable);

    void updateMovie(Long id, String title, EMovieCategory category, int length,
                     String description, int requiredAge, String posterFilePath);

    void removeMovie(Long id);

}
