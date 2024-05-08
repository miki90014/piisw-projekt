package com.example.cinema.reservation;


import com.example.cinema.availableSeats.AvailableSeats;
import com.example.cinema.availableSeats.AvailableSeatsRepository;
import com.example.cinema.availableSeats.SeatStatus;
import com.example.cinema.movie.Movie;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public List<Reservation> getReservations() {return (List<Reservation>) reservationRepository.findAll();}

    @GetMapping("/tickets/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get("qr-ticket/"+imageName+".png");
        UrlResource resource = new UrlResource(imagePath.toUri());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(Path.of(resource.getURI()));
        MediaType mediaType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
    }

    @GetMapping("/reservations/{id}")
    public ReservationDAO getReservationById(@PathVariable("id") Long id) throws InterruptedException {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            return null;
        }
        Reservation reservation = optionalReservation.get();
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.setPersonData(reservation.getPersonData());
        reservationDAO.setTotalPrice(reservation.getTotalPrice());
        reservationDAO.setTicket(reservation.getTicket());
        List<ListOfReservedSeats> listOfReservedSeats = listOfReservedSeatsRepository.findByReservationId(id);
        if (listOfReservedSeats.isEmpty()) {
            return null;
        }
        ArrayList<AvailableSeats> availableSeatsArrayList = new ArrayList<>();
        for (ListOfReservedSeats reservedSeats: listOfReservedSeats){
            availableSeatsArrayList.add(reservedSeats.getReservedSeat());
        }
        reservationDAO.setReservedSeats(availableSeatsArrayList);

        return reservationDAO;
    }
    @PostMapping("/reservations")
    public Long createReservation(@RequestBody ReservationDAO reservationDAO) throws WriterException, IOException {
        Reservation reservation = new Reservation();
        reservation.setTotalPrice(reservationDAO.getTotalPrice());
        reservation.setPersonData(reservationDAO.getPersonData());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        UUID uuid = UUID.randomUUID();
        BitMatrix bitMatrix = qrCodeWriter.encode(String.valueOf(uuid), BarcodeFormat.QR_CODE, 150, 150);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        File outputfile = new File("qr-ticket/"+uuid+".png");
        ImageIO.write(bufferedImage, "png", outputfile);
        reservation.setTicket(String.valueOf(uuid));
        reservationRepository.save(reservation);
        for (AvailableSeats availableSeat : reservationDAO.getReservedSeats()){
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

