package com.cschool.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
//    @Column(name = "movie_id")
    private Long movieId;
//    @Column(name = "file_path")
    private String filePath;
    @OneToOne
    @JoinColumn(name = "movie", referencedColumnName = "id")
    private Movie movie;
}
