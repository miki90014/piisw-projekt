package com.example.cinema.reservation;

import com.example.cinema.availableseats.AvailableSeats;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ListOfReservedSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AvailableSeats reservedSeat;
    @ManyToOne
    private Reservation reservation;
}
