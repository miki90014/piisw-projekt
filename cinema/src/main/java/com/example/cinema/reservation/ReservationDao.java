package com.example.cinema.reservation;

import com.example.cinema.availableseats.AvailableSeats;
import lombok.Data;

import java.util.ArrayList;

@Data

public class ReservationDao {
    private Double totalPrice;
    private String personData;
    private ArrayList<AvailableSeats> reservedSeats;
    private String ticket;
}
