package com.example.cinema.availableSeats;

import com.example.cinema.seance.Seance;
import com.example.cinema.seat.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class AvailableSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = EAGER)
    private Seance seance;
    @ManyToOne(fetch = EAGER)
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
    private Double price;
}
