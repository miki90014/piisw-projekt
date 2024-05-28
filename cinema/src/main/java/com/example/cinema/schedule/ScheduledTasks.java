package com.example.cinema.schedule;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import com.example.cinema.availableseats.SeatStatus;
import com.example.cinema.reservation.ListOfReservedSeats;
import com.example.cinema.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final AvailableSeatsRepository availableSeatsRepository;

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
    public void changeStatusToExpire() {
        LocalDateTime futureDateTime = LocalDateTime.now().plusMinutes(15);
        Timestamp futureTimestamp = Timestamp.valueOf(futureDateTime);
        List<AvailableSeats> ticketsToBeExpired = availableSeatsRepository.findTicketsWithTodayDate(futureTimestamp);

        for (AvailableSeats availableSeats : ticketsToBeExpired) {
            availableSeats.setSeatStatus(SeatStatus.EXPIRED);
            availableSeatsRepository.save(availableSeats);
        }
    }


}
