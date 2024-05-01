package com.example.cinema.seat;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, Long> {
    List<Seat> findByRoomId(Long roomId);
}
