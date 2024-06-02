package com.example.cinema;

import com.example.cinema.reservation.*;
import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import com.example.cinema.availableseats.SeatStatus;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private AvailableSeatsRepository availableSeatsRepository;
    @Mock
    private ListOfReservedSeatsRepository listOfReservedSeatsRepository;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation reservation1;
    private List<AvailableSeats> availableSeatsList;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setPersonData("John Doe");
        reservation1.setTotalPrice(200.00);
        reservation1.setTicket("ticket-uuid");

        AvailableSeats seat = new AvailableSeats();
        seat.setId(1L);
        seat.setSeatStatus(SeatStatus. EMPTY);
        availableSeatsList = new ArrayList<>();
        availableSeatsList.add(seat);
    }

    @Test
    public void shouldReturnAllReservations() throws Exception {
        List<Reservation> reservations = List.of(reservation1);
        given(reservationRepository.findAll()).willReturn(reservations);

        mockMvc.perform(get("/reservations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(reservation1.getId()))
                .andExpect(jsonPath("$[0].personData").value(reservation1.getPersonData()))
                .andExpect(jsonPath("$[0].totalPrice").value(reservation1.getTotalPrice()))
                .andDo(print());
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
    public void shouldGenerateQRCode() throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("ticket-uuid", BarcodeFormat.QR_CODE, 150, 150);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        mockMvc.perform(get("/tickets/{imageName}", "ticket-uuid")
                        .contentType(MediaType.IMAGE_PNG))
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageBytes))
                .andDo(print());
    }
}
