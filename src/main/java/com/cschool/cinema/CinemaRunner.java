package com.cschool.cinema;

import com.cschool.cinema.domain.*;
import com.cschool.cinema.service.MarathonService;
import com.cschool.cinema.service.MovieService;
import com.cschool.cinema.service.SessionService;
import com.cschool.cinema.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@Component
@Slf4j
@AllArgsConstructor
public class CinemaRunner implements CommandLineRunner {

    private MarathonService marathonService;
    private MovieService movieService;
    private SessionService sessionService;
    private TicketService ticketService;

    @Override
    public void run(String... args) throws Exception {
        movieServiceInvocation();
        sessionServiceInvocations();
        ticketServiceInvocations();
        marathonServiceInvocations();
    }

    private void movieServiceInvocation() {
        Page<Movie> allMovies = movieService.getAllMovies(PageRequest.of(1, 3, Sort.by("title")));
        log.info("1. All movies. TotalElements={}, TotalPages={}", allMovies.getTotalElements(), allMovies.getTotalPages());
        allMovies.get().forEach(movie -> log.info("    {}", movie));

        Page<Movie> moviesPartOFTitles = movieService.findByTitleContaining("Gry", Pageable.unpaged());
        log.info("2.Movies by part of title. TotalElements={}, TotalPages={}", moviesPartOFTitles.getTotalElements(), moviesPartOFTitles.getTotalPages());
        moviesPartOFTitles.get().forEach(movie -> log.info("    {}", movie));

        Page<Movie> moviesByCategory = movieService.findByCategory(EMovieCategory.ACTION, Pageable.unpaged());
        log.info("3.Movies by category, TotalElements={}", moviesByCategory.getTotalElements());
        moviesByCategory.get().forEach(movie -> log.info("  {}", movie));

        Movie movieWithId7 = movieService.getMovie(7L).get();
        log.info("4. Movie with id 7 ={}", movieWithId7);
        movieService.updateMovie(7L, movieWithId7.getTitle(), EMovieCategory.WESTERN, 140, "nowy ipdate", 100, "new/poster/path");

        Movie movieWithId7AfterUpdate = movieService.getMovie(7L).get();
        log.info("5.Movie after update with id 7={} ", movieWithId7AfterUpdate);

        Long newMovieId = movieService.createMovie("Jak wytresować smoka", EMovieCategory.FAMILY, 90, "Czkawka jest kiepskim wikingiem..", 0, "poster/posterJakwytresowaćSmoka");
        log.info("6.id nowo dodanego filmu to = {}", newMovieId);

        movieService.removeMovie(7L);
    }

    private void sessionServiceInvocations() {
        List<Session> sessionIn21102019 = sessionService.getSessionInDate(LocalDate.of(2019, 10, 21));
        log.info("7. sessionIn21102019 AmountOfSessions={}", sessionIn21102019.size());
        sessionIn21102019.forEach(session -> log.info("    {}", session));

        Session sessionWithId15 = sessionService.getSessionById(15L).get();
        log.info("8. sessionWithId15= {}, MovieTitle={}, Room={}", sessionWithId15, sessionWithId15.getMovie().getTitle(), sessionWithId15.getRoom().getName());
        //sessionWithId15.getTickets().forEach(ticket -> log.info("   {}", ticket));

        Session sessionWithId15WithTickets = sessionService.getSessionWithTickets(15L).get();
        log.info("9. sessionWithIdl5withTickets - {}, MovieTitle= '{}', Room='{}'", sessionWithId15WithTickets, sessionWithId15WithTickets.getMovie().getTitle(), sessionWithId15WithTickets.getRoom().getName());
        sessionWithId15WithTickets.getTickets().forEach(ticket -> log.info("    {}", ticket));

        Long newSessionId = sessionService.createSession(11L, 4L, LocalDateTime.of(2019, 5, 3, 10, 30));
        Session newSession = sessionService.getSessionById(newSessionId).get();
        log.info("10. newSession - {}", newSession);

        sessionService.removeSession(16L);

    }
    private void ticketServiceInvocations () {
        List<Ticket> ticketsOnSessionWithId15 = ticketService.findAllTicketsBySession(15L);
        log.info("11. ticketsOnSessionWithId15. AmountOfTickets ={}", ticketsOnSessionWithId15.size());
        ticketsOnSessionWithId15.forEach(ticket -> log.info("   {}", ticket));
        Long newTicketId = ticketService.createTicket(15L, "r2m5", new BigDecimal("22.50"));
        log.info("12. newTicketId={}", newTicketId);
        ticketService.removeTicket(18L);
        List<Ticket> ticketsOnSessionWithIdlSAfterUpdates = ticketService.findAllTicketsBySession(15L);
        log.info("13. ticketsOnSessionWithIdl5AfterUpdates. AmountOfTickets={}", ticketsOnSessionWithIdlSAfterUpdates.size());
        ticketsOnSessionWithIdlSAfterUpdates.forEach(ticket -> log.info("   {}", ticket));
    }

    private void marathonServiceInvocations () {
        Long newMarathonId = marathonService.createMarathon( Arrays.asList(6L, 8L, 9L),"Maraton horrorów, 30.10.2018", LocalDateTime.of(2018, 10, 30, 20, 0));
        Marathon newMarathon = marathonService.getMarathonById(newMarathonId).get();
        log.info("14. newHarathon - {}", newMarathon);
        newMarathon.getMovies().forEach(movie -> log.info("     {}",movie));
        List<Marathon> marathons = marathonService.getAllMarathons(Pageable.unpaged());
        log.info("15. marathons. AmountOfHarathons=(])", marathons.size());
        marathons.forEach(marathon -> log.info("    {}", marathon));
        marathonService.deleteMarathon(newMarathonId);
    }
}