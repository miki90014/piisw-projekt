package com.example.cinema.reservation;

import org.springframework.data.repository.CrudRepository;

public interface ListOfReservedSeatsRepository extends CrudRepository<ListOfReservedSeats, Long> {
}
