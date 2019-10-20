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
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
//    @Column(name = "movie_id")
    private Long movieId;
//    @Column(= "room_id")
    private Long roomId;
//    @Column(name = "start_time")
    private LocalDateTime startTime;
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
    @ManyToOne
    @JoinColumn (name = "movie")
    private Movie movie;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private List<Ticket> tickets;

    public List<Ticket> getTickets(){
        if(tickets == null){
            tickets = new ArrayList<>();
        }
        return tickets;
    }
    public void addTicket(Ticket ticket){
        getTickets().add(ticket);
        ticket.setSession(this);
    }
}
