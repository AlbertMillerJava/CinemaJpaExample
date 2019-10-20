package com.cschool.cinema;

import com.cschool.cinema.domain.EMovieCategory;
import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.service.MarathonService;
import com.cschool.cinema.service.MovieService;
import com.cschool.cinema.service.SessionService;
import com.cschool.cinema.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class CinemaRunner implements CommandLineRunner {

    private MarathonService marathonService;
    private MovieService movieService;
    private SessionService sessionService;
    private TicketService ticketService;

    @Override
    public void run(String... args) throws Exception {
        movieServiceInvocation();
    }
    private void movieServiceInvocation(){
        Page<Movie> allMovies = movieService.getAllMovies(PageRequest.of(1,3, Sort.by("title")));
        log.info("1. All movies. TotalElements={}, TotalPages={}",allMovies.getTotalElements(),allMovies.getTotalPages());
        allMovies.get().forEach(movie -> log.info("    {}", movie));

        Page<Movie> moviesPartOFTitles = movieService.findByTitleContaining("Gry", Pageable.unpaged());
        log.info("2.Movies by part of title. TotalElements={}, TotalPages={}", moviesPartOFTitles.getTotalElements(),moviesPartOFTitles.getTotalPages());
        moviesPartOFTitles.get().forEach(movie -> log.info("    {}",movie));

        Page<Movie> moviesByCategory = movieService.findByCategory(EMovieCategory.ACTION,Pageable.unpaged());
        log.info("3.Movies by category, TotalElements={}", moviesByCategory.getTotalElements());
        moviesByCategory.get().forEach(movie -> log.info("  {}",movie));

        Optional<Movie> movieWithId7 = movieService.getMovie(7L);
        log.info("4. Movie with id 7", movieWithId7.get());
    }
}
