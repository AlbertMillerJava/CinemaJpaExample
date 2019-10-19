package com.cschool.cinema.repository;

import com.cschool.cinema.domain.EMovieCategory;
import com.cschool.cinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long > {

    Page<Movie> findByCategory(EMovieCategory category, Pageable pageable);

    Page<Movie> findByTitleContaining(String partOfTitle, Pageable pageable);
}
