package com.example.cinema.availableseats;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AvailableSeatsRepository extends CrudRepository<AvailableSeats, Long> {
    @Query("SELECT a FROM AvailableSeats a WHERE a.seance.id = :seanceId")
    List<AvailableSeats> findAvailableSeatsBySeanceId(@Param("seanceId") Long seanceId);

    @Query("SELECT a FROM AvailableSeats a WHERE a.seance.dateOfSeance >= :futureDate AND (a.seatStatus!= 'EXPIRED' OR a.seatStatus!= 'EMPTY')")
    List<AvailableSeats> findTicketsWithTodayDate(@Param("futureDate") Timestamp futureDate);
}
