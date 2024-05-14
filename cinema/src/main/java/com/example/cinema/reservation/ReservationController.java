package com.example.cinema.reservation;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import com.example.cinema.availableseats.SeatStatus;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final AvailableSeatsRepository availableSeatsRepository;
    private final ListOfReservedSeatsRepository listOfReservedSeatsRepository;

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @GetMapping("/tickets/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(imageName, BarcodeFormat.QR_CODE, 150, 150);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        MediaType mediaType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
    }

    @GetMapping("/reservations/{id}")
    public ReservationDao getReservationById(@PathVariable("id") Long id) throws InterruptedException {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            return null;
        }
        Reservation reservation = optionalReservation.get();
        ReservationDao reservationDao = new ReservationDao();
        reservationDao.setPersonData(reservation.getPersonData());
        reservationDao.setTotalPrice(reservation.getTotalPrice());
        reservationDao.setTicket(reservation.getTicket());
        List<ListOfReservedSeats> listOfReservedSeats = listOfReservedSeatsRepository.findByReservationId(id);
        if (listOfReservedSeats.isEmpty()) {
            return null;
        }
        ArrayList<AvailableSeats> availableSeatsArrayList = new ArrayList<>();
        for (ListOfReservedSeats reservedSeats : listOfReservedSeats) {
            availableSeatsArrayList.add(reservedSeats.getReservedSeat());
        }
        reservationDao.setReservedSeats(availableSeatsArrayList);

        return reservationDao;
    }

    @PostMapping("/reservations")
    public Long createReservation(@RequestBody ReservationDao reservationDao) throws WriterException, IOException {
        Reservation reservation = new Reservation();
        reservation.setTotalPrice(reservationDao.getTotalPrice());
        reservation.setPersonData(reservationDao.getPersonData());
        UUID uuid = UUID.randomUUID();
        reservation.setTicket(String.valueOf(uuid));
        reservationRepository.save(reservation);
        for (AvailableSeats availableSeat : reservationDao.getReservedSeats()) {
            ListOfReservedSeats listOfReservedSeats = new ListOfReservedSeats();
            listOfReservedSeats.setReservedSeat(availableSeat);
            listOfReservedSeats.setReservation(reservation);
            listOfReservedSeatsRepository.save(listOfReservedSeats);
            availableSeat.setSeatStatus(SeatStatus.RESERVED);
            availableSeatsRepository.save(availableSeat);
        }
        return reservation.getId();
    }
}

