package com.cschool.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(columnDefinition = "movie_id")
    private Long movieId;
    @Column(columnDefinition = "file_path")
    private String filePath;
    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
}
