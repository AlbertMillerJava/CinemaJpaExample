package com.cschool.cinema.controller;

import com.cschool.cinema.boundary.dto.TicketDto;
import com.cschool.cinema.domain.Ticket;
import com.cschool.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTicket(@RequestBody TicketDto ticketDto){
        Ticket ticket = ticketDto.createTicketFromDto();
        return ticketService.createTicket(ticket.getSession().getId(),ticket.getSeat(),ticket.getPrice());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getTicketBySessionId(Long id){
        return ticketService.findAllTicketsBySession(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTicket(Long id){
        ticketService.removeTicket(id);
    }

}
