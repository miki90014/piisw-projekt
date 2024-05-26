package com.example.cinema;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsController;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvailableSeatsControllerTests {
    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvailableSeatsRepository availableSeatsRepository;

    @InjectMocks
    private AvailableSeatsController availableSeatsController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAvailableSeats() throws Exception {
        AvailableSeats seat = new AvailableSeats();
        ArrayList<AvailableSeats> availableSeatsArrayList = new ArrayList<>();
        availableSeatsArrayList.add(seat);

        Mockito.when(availableSeatsRepository.findAll()).thenReturn(availableSeatsArrayList);

        mockMvc.perform(get("/available_seats"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(seat))));
    }

    @Test
    public void testGetAvailableSeatsById() throws Exception {
        AvailableSeats seat = new AvailableSeats();
        Mockito.when(availableSeatsRepository.findById(anyLong())).thenReturn(Optional.of(seat));

        mockMvc.perform(get("/available_seats/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(seat)));
    }

    @Test
    public void testGetAvailableSeatsByIdNotFound() throws Exception {
        Mockito.when(availableSeatsRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/available_seats/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetEmptyAvailableSeatsById() throws Exception {
        AvailableSeats seat = new AvailableSeats();
        Mockito.when(availableSeatsRepository.findAvailableSeatsBySeanceId(anyLong())).thenReturn(Collections.singletonList(seat));

        mockMvc.perform(get("/available_seats/seance/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(seat))));
    }*/
}
