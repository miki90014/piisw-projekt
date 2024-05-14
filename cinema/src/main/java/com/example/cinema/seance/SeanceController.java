package com.example.cinema.seance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SeanceController {
    private final SeanceRepository seanceRepository;

    @GetMapping("/movies/{movieId}/seances")
    public List<Seance> getSeances(@PathVariable ("movieId") Long movieId) {
        List<Seance> seances = seanceRepository.findByMovieId(movieId);
        if (!seances.isEmpty()) {
            return seances;
        } else {
            return null; //obsługa błedów, jak inaczej?
        }
    }

    @GetMapping("/seances/{seanceId}")
    public Seance getSeancesById(@PathVariable ("seanceId") Long seanceId) {
        Optional<Seance> optionalSeance = seanceRepository.findById(seanceId);
        if (optionalSeance.isPresent()) {
            return optionalSeance.get();
        }
        return null;
    }
}
