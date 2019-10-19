package com.cschool.cinema.repository;

import com.cschool.cinema.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
