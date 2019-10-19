package com.cschool.cinema.service;

import com.cschool.cinema.domain.EMovieCategory;
import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.domain.Poster;
import com.cschool.cinema.exception.EntityDoesNotExistsException;
import com.cschool.cinema.repository.MovieRepository;
import com.cschool.cinema.repository.PosterRepository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private PosterRepository posterRepository;



    @Override
    @Transactional
    public Long createMovie(String title, EMovieCategory category, int length,
                            String description, int requiredAge, String posterFilePath) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setLength(length);
        movie.setDescription(description);
        movie.setRequiredAge(requiredAge);
        movieRepository.save(movie);
        if (posterFilePath != null){
            createPoster(movie,posterFilePath);
        }
        return movie.getId();
    }

    @Override
    public Page<Movie> findByCategory(EMovieCategory category, Pageable pageable) {
        return movieRepository.findByCategory(category, pageable);
    }
    private void createPoster(Movie movie, String posterFilePath){
        Poster poster = new Poster();
        poster.setMovie(movie);
        poster.setFilePath(posterFilePath);
        posterRepository.save(poster);
    }
    @Override
    public Page<Movie> findByTitleContaining(String partOfTitle, Pageable pageable) {
        return movieRepository.findByTitleContaining(partOfTitle,pageable);
    }

    @Override
    public Optional<Movie> getMovie(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateMovie(Long id, String title, EMovieCategory category, int length,
                            String description, int requiredAge, String posterFilePath) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(!movieOptional.isPresent()){
            throw new EntityDoesNotExistsException("movie id=" + id);
        }
        Movie movie = movieOptional.get();
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setLength(length);
        movie.setDescription(description);
        movie.setRequiredAge(requiredAge);
        movieRepository.save(movie);

        Optional<Poster> posterOptional = posterRepository.findByMovie(movie);
        if(posterOptional.isPresent()){
            Poster poster = posterOptional.get();
            if(posterFilePath != null){
                poster.setFilePath(posterFilePath);
                posterRepository.save(poster);
            }
            else{
                posterRepository.delete(poster);
            }
        }else if(posterFilePath != null){
            createPoster(movie,posterFilePath);
        }
    }

    @Override
    public void removeMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
