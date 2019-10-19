package com.cschool.cinema.service;

import com.cschool.cinema.domain.Session;
import com.cschool.cinema.domain.Ticket;
import com.cschool.cinema.exception.EntityDoesNotExistsException;
import com.cschool.cinema.iterable.IterableUtils;
import com.cschool.cinema.repository.SessionRepository;
import com.cschool.cinema.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private SessionRepository sessionRepository;
    private TicketRepository ticketRepository;
    @Override
    public Long createTicket(Long sessionId, String seat, BigDecimal price) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isEmpty()){
            throw new EntityDoesNotExistsException("sessionId= "+ sessionId);
        }
        Ticket ticket = new Ticket();
        ticket.setSession(optionalSession.get());
        ticket.setSeat(seat);
        ticket.setPrice(price);
        ticketRepository.save(ticket);

        return ticket.getId();
    }

    @Override
    public List<Ticket> findAllTicketsBySession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isEmpty()){
            throw new EntityDoesNotExistsException("sessionId= "+ sessionId);
        }
        return IterableUtils.iterableToList(ticketRepository.findAllBySession(optionalSession.get()));
    }

    @Override
    @Transactional
    public void removeTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
