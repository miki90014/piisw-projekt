package com.example.cinema.availableSeats;

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
public class AvailableSeatsController {
    private final AvailableSeatsRepository availableSeatsRepository;
    @GetMapping ("/available_seats")
    public List<AvailableSeats> getAvailableSeats() { return (List<AvailableSeats>) availableSeatsRepository.findAll(); }

    @GetMapping("/available_seats/{id}")
    public AvailableSeats getAvailableSeatsById(@PathVariable ("id") Long id) {
     Optional<AvailableSeats> availableSeats = availableSeatsRepository.findById(id);
     if (availableSeats.isPresent()) {
         return availableSeats.get();
        }
     return null;
    }
}
