package com.example.cinema.seance;

import com.example.cinema.movie.Movie;
import com.example.cinema.room.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Movie movie;
    @ManyToOne(fetch = LAZY)
    private Room room;
    private LocalDate dateOfSeance;
}
