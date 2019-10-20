package com.cschool.cinema.controller;

import com.cschool.cinema.boundary.dto.SessionDto;
import com.cschool.cinema.domain.Session;
import com.cschool.cinema.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping(path = "/add")
    Long createSession(@RequestBody SessionDto sessionDto) {
        Session session = sessionDto.createSessionFromDto();
        return sessionService.createSession(session.getMovie().getId(), session.getRoom().getId(), session.getStartTime());
    }

}
