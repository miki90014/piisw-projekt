package com.example.cinema.reservation;

import com.example.cinema.availableSeats.AvailableSeats;
import com.example.cinema.seance.Seance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private String personData;
}
