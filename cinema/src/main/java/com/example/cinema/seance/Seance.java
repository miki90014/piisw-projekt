package com.example.cinema.seance;

import com.example.cinema.movie.Movie;
import com.example.cinema.room.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = EAGER)
    private Movie movie;
    @ManyToOne(fetch = EAGER)
    private Room room;
    private Timestamp dateOfSeance;
}
