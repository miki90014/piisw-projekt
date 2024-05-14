package com.example.cinema.availableseats;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailableSeatsRepository extends CrudRepository<AvailableSeats, Long> {
    @Query("SELECT a FROM AvailableSeats a WHERE a.seance.id = :seanceId")
    List<AvailableSeats> findAvailableSeatsBySeanceId(@Param("seanceId") Long seanceId);
}
