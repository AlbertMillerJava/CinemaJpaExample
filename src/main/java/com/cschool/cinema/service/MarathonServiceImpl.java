package com.cschool.cinema.service;

import com.cschool.cinema.domain.Marathon;
import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.domain.Session;
import com.cschool.cinema.exception.EntityDoesNotExistsException;
import com.cschool.cinema.iterable.IterableUtils;
import com.cschool.cinema.repository.MarathonRepository;
import com.cschool.cinema.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MarathonServiceImpl implements MarathonService{

    private MovieRepository movieRepository;
    private MarathonRepository marathonRepository;

    @Override
    @Transactional
    public Long createMarathon(List<Long> moviesIds, String name, LocalDateTime startTime) {
        List<Movie> movies = new ArrayList<>();
        for (Long movieId : moviesIds ){
            Optional<Movie> movieOptional = movieRepository.findById(movieId);
            if (movieOptional.isEmpty()){
                throw new EntityDoesNotExistsException("Movie, id=" + movieId);
            }
            movies.add(movieOptional.get());
        }
        Marathon marathon = new Marathon();
        marathon.setMovies(movies);
        marathon.setName(name);
        marathon.setStartTime(startTime);

        for(Movie movie:movies){
            movie.getMarathons().add(marathon);
        }
        marathonRepository.save(marathon);
        return marathon.getId();
    }

    @Override
    public Optional<Marathon> getMarathonById(Long id) {

        return marathonRepository.findById(id);
    }

    @Override
    public List<Marathon> getAllMarathons(Pageable pageable) {
        return IterableUtils.iterableToList(marathonRepository.findAll());
    }

    @Override
    @Transactional
    public void deleteMarathon(Long id) {
        marathonRepository.deleteById(id);
    }
}
