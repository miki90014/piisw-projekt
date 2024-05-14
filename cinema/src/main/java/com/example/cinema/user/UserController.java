package com.example.cinema.user;

import com.example.cinema.availableSeats.AvailableSeats;
import com.example.cinema.availableSeats.AvailableSeatsRepository;
import com.example.cinema.availableSeats.SeatStatus;
import com.example.cinema.reservation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    private final ReservationRepository reservationRepository;
    private final AvailableSeatsRepository availableSeatsRepository;
    private final ListOfReservedSeatsRepository listOfReservedSeatsRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDAO loginRequest) {
        if (userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            return ResponseEntity.ok("simple-token");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/user/check")
    public ReservationDAO checkReservation(@RequestParam String ticket) {
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.setTicket("UNFOUNDED");

        Optional<Reservation> optionalReservation = reservationRepository.findByTicket(ticket);
        if (optionalReservation.isEmpty()) {
            return reservationDAO;
        }

        Reservation reservation = optionalReservation.get();

        reservationDAO.setPersonData(reservation.getPersonData());
        reservationDAO.setTotalPrice(reservation.getTotalPrice());
        reservationDAO.setTicket(reservation.getTicket());

        List<ListOfReservedSeats> listOfReservedSeats = listOfReservedSeatsRepository.findByReservationId(reservation.getId());
        if (listOfReservedSeats.isEmpty()) {
            return reservationDAO;
        }

        ArrayList<AvailableSeats> availableSeatsArrayList = new ArrayList<>();
        for (ListOfReservedSeats reservedSeats: listOfReservedSeats){
            AvailableSeats availableSeats = reservedSeats.getReservedSeat();
            if (availableSeats.getSeatStatus() == SeatStatus.RESERVED) {
                availableSeats.setSeatStatus(SeatStatus.VALIDATED);
                availableSeatsRepository.save(availableSeats);
            }
            else if(availableSeats.getSeatStatus() == SeatStatus.VALIDATED) {
                availableSeats.setSeatStatus(SeatStatus.ALREADY_VALIDATED);
            }
            availableSeatsArrayList.add(reservedSeats.getReservedSeat());
        }

        reservationDAO.setReservedSeats(availableSeatsArrayList);
        return  reservationDAO;
    }
}
