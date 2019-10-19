package com.cschool.cinema.service;

import com.cschool.cinema.domain.Marathon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarathonService {

    Long createMarathon(List<Long> moviesIds, String name, LocalDateTime startTime);

    Optional<Marathon> getMarathonById(Long id);

    List<Marathon> getAllMarathons(Pageable pageable);

    void deleteMarathon(Long id);
}
