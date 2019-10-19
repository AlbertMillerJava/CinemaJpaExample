package com.cschool.cinema.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(columnDefinition = "start_time")
    private LocalDateTime startTime;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
        name = "marathon_movie",
        joinColumns = @JoinColumn(name = "marathon_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @Getter(AccessLevel.NONE)
    private List<Movie> movies;

    public List<Movie> getMovies(){
        if(movies == null){
            movies = new ArrayList<>();
        }
        return movies;
    }

}
