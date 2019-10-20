package com.cschool.cinema.service;

import com.cschool.cinema.domain.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionService {

    Long createSession(Long movieId, Long roomId, LocalDateTime startTime);

    Optional<Session> getSessionById(Long id);

    Optional<Session> getSessionWithTickets(Long id);

    List<Session> getSessionInDate(LocalDate date);

    void removeSession (Long id);
}
