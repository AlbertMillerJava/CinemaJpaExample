package com.cschool.cinema.repository;

import com.cschool.cinema.domain.Session;
import com.cschool.cinema.domain.Ticket;
import org.springframework.data.repository.CrudRepository;


public interface TicketRepository extends CrudRepository<Ticket,Long> {
    Iterable<Ticket> findAllBySession(Session session);

    Long countBySession (Session session);
}
