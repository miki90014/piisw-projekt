package com.example.cinema.availableSeats;

import com.example.cinema.seance.Seance;
import com.example.cinema.seat.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class AvailableSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    private Seance seance;
    @ManyToOne(fetch = LAZY)
    private Seat seat;
    private boolean reserved;
    private String ticket;
    private Double price;
}
