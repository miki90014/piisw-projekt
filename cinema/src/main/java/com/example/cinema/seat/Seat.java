package com.example.cinema.seat;

import com.example.cinema.room.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private char seatRow;
    private int number;
    @ManyToOne(fetch = EAGER)
    Room room;
}
