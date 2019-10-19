package com.cschool.cinema.repository;

import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.domain.Poster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PosterRepository extends CrudRepository<Poster,Long> {
    Optional<Poster> findByMovie (Movie movie);
}
