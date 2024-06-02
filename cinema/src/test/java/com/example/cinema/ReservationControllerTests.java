package com.example.cinema;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import com.example.cinema.reservation.*;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class ReservationControllerTests {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private AvailableSeatsRepository availableSeatsRepository;
    @Mock
    private ListOfReservedSeatsRepository listOfReservedSeatsRepository;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    public void shouldGetReservations() {
        // GIVEN
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(new Reservation(), new Reservation()));

        // WHEN
        var result = reservationController.getReservations();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetImage() throws IOException, WriterException {
        // WHEN
        ResponseEntity<byte[]> response = reservationController.getImage("testQRCode");

        // THEN
        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    public void shouldGetReservationById() throws InterruptedException {
        // GIVEN
        Reservation mockReservation = new Reservation();
        mockReservation.setTotalPrice(100.0);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(mockReservation));
        when(listOfReservedSeatsRepository.findByReservationId(1L)).thenReturn(Arrays.asList(new ListOfReservedSeats()));

        // WHEN
        ReservationDao result = reservationController.getReservationById(1L);

        // THEN
        assertNotNull(result);
        assertEquals(100.0, result.getTotalPrice());
    }

    @Test
    public void testCreateReservation() throws IOException, WriterException {
        // GIVEN
        ReservationDao reservationDao = new ReservationDao();
        reservationDao.setTotalPrice(150.0);
        reservationDao.setReservedSeats(new ArrayList<>(Arrays.asList(new AvailableSeats())));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
            Reservation saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        // WHEN
        Long reservationId = reservationController.createReservation(reservationDao);

        // THEN
        assertNotNull(reservationId);
        assertEquals(1L, reservationId);
    }

}
