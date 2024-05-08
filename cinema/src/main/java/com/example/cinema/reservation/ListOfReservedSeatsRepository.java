package com.example.cinema.reservation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListOfReservedSeatsRepository extends CrudRepository<ListOfReservedSeats, Long> {
    List<ListOfReservedSeats> findByReservationId(Long reservationId);
}
