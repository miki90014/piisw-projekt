package com.example.cinema.reservation;


import com.example.cinema.availableSeats.AvailableSeats;
import com.example.cinema.availableSeats.AvailableSeatsRepository;
import com.example.cinema.availableSeats.SeatStatus;
import com.example.cinema.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final AvailableSeatsRepository availableSeatsRepository;
    private final ListOfReservedSeatsRepository listOfReservedSeatsRepository;

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {return (List<Reservation>) reservationRepository.findAll();}

    @GetMapping("/reservations/{id}")
    public Reservation getReservationById(@PathVariable("id") Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            return reservationRepository.findById(id).get();
        }
    return null;
    }
    @PostMapping("/reservations")
    public void createReservation(@RequestBody ReservationDAO reservationDAO) {
        Reservation reservation = new Reservation();
        reservation.setTotalPrice(reservationDAO.getTotalPrice());
        reservation.setPersonData(reservationDAO.getPersonData());
        reservationRepository.save(reservation);
        for (AvailableSeats availableSeat : reservationDAO.getReservedSeats()){
            ListOfReservedSeats listOfReservedSeats = new ListOfReservedSeats();
            listOfReservedSeats.setReservedSeat(availableSeat);
            listOfReservedSeats.setReservation(reservation);
            listOfReservedSeatsRepository.save(listOfReservedSeats);
            availableSeat.setSeatStatus(SeatStatus.RESERVED);
            availableSeatsRepository.save(availableSeat);

        }

    }
}

