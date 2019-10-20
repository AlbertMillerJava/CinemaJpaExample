package com.cschool.cinema.boundary.dto;

import com.cschool.cinema.domain.EMovieCategory;
import com.cschool.cinema.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private String title;
    @Enumerated(EnumType.STRING)
    private EMovieCategory category;
    private int length;
    private String description;
    private int requiredAge;
    private String posterFilePath;

    public Movie createMovieFromDto() {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setRequiredAge(requiredAge);
        movie.setLength(length);
        movie.setDescription(description);
        return movie;
    }
}
