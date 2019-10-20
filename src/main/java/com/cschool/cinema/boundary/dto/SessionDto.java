package com.cschool.cinema.boundary.dto;

import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.domain.Room;
import com.cschool.cinema.domain.Session;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SessionDto {

    private Long movieId;
    private Long roomId;
    private LocalDateTime startTime;

    public Session createSessionFromDto(){
        Session session = new Session();
        Movie movie = new Movie();
        Room room = new Room();
        movie.setId(movieId);
        room.setId(roomId);
        session.setStartTime(startTime);
        session.setRoom(room);
        session.setMovie(movie);
        return session;
    }
}
