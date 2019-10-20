package com.cschool.cinema.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private EMovieCategory category;
    private int length;
    private String description;
    @Column(name = "required_age")
    private int requiredAge;
    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    private List<Marathon> marathons;

    public List<Marathon> getMarathons(){
        if (marathons == null){
            marathons = new ArrayList<>();
        }
        return marathons;
    }
}
