package com.example.cinema.availableSeats;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailableSeatsRepository extends CrudRepository<AvailableSeats, Long> {
    @Query("SELECT a FROM AvailableSeats a WHERE a.seatStatus = :seatStatus AND a.seance.id = :seanceId")
    List<AvailableSeats> findAvailableSeatsByStatusAndSeanceId(@Param("seatStatus") SeatStatus seatStatus, @Param("seanceId") Long seanceId);
}
