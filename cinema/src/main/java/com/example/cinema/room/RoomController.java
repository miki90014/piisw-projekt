package com.example.cinema.room;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor

public class RoomController {
    private final RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomsById(@PathVariable("id") Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        }
        return null;
    }
}