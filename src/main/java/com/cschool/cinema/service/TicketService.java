package com.cschool.cinema.service;

import com.cschool.cinema.domain.Ticket;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {

    Long createTicket(Long sessionId, String seat, BigDecimal price);

    List<Ticket> findAllTicketsBySession(Long sessionId);

    void removeTicket(Long ticketId);



}
