package com.example.cinema.seat;

import com.example.cinema.seance.Seance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SeatController {
    private final SeatRepository seatRepository;

    @GetMapping ("/rooms/{roomId}/seats")
    public List<Seat> getSeats(@PathVariable ("roomId") Long roomId) {
        List<Seat> seats = seatRepository.findByRoomId(roomId);
        if (!seats.isEmpty()) {
            return seats;
        } else {
            return null; //obsługa błedów, jak inaczej?
        }
    }

    @GetMapping("/seats/{id}")
    public Seat getSeatsById(@PathVariable ("id") Long id) {
        Optional<Seat> optionalSeat = seatRepository.findById(id);
        if (optionalSeat.isPresent()) {
            return optionalSeat.get();
        }
        return null;
    }
}
