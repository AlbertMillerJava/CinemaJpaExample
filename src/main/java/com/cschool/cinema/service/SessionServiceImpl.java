package com.cschool.cinema.service;

import com.cschool.cinema.domain.Movie;
import com.cschool.cinema.domain.Room;
import com.cschool.cinema.domain.Session;
import com.cschool.cinema.exception.EntityDoesNotExistsException;
import com.cschool.cinema.repository.MovieRepository;
import com.cschool.cinema.repository.RoomRepository;
import com.cschool.cinema.repository.SessionRepository;
import com.cschool.cinema.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;
    TicketRepository ticketRepository;
    MovieRepository movieRepository;
    RoomRepository roomRepository;

    @Override
    @Transactional
    public Long createSession(Long movieId, Long roomId, LocalDateTime startTime) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new EntityDoesNotExistsException("movieId=" + movieId);
        }
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()){
            throw new EntityDoesNotExistsException("roomId="+ roomId);
        }
        Session session = new Session();
        session.setMovie(movieOptional.get());
        session.setRoom(roomOptional.get());
        session.setStartTime(startTime);
        sessionRepository.save(session);

        return session.getId();
    }

    @Override
    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Session> getSessionWithTickets(Long id) {
        return sessionRepository.readById(id);
    }

    @Override
    public List<Session> getSessionInDate(LocalDate date) {
        return sessionRepository.findAllByStartDate(Date.valueOf(date));
    }

    @Override
    @Transactional
    public void removeSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
