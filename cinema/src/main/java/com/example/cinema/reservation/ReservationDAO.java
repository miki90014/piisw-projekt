package com.example.cinema.reservation;

import com.example.cinema.availableSeats.AvailableSeats;
import lombok.Data;

import java.util.ArrayList;

@Data

public class ReservationDAO {
    private Double totalPrice;
    private String personData;
    private ArrayList<AvailableSeats> reservedSeats;
}