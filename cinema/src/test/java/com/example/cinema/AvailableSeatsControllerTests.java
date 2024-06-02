package com.example.cinema;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvailableSeatsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvailableSeatsRepository availableSeatsRepository;

    private AvailableSeats seat1;
    private AvailableSeats seat2;

    @BeforeEach
    void setUp() {
        seat1 = new AvailableSeats();
        seat2 = new AvailableSeats();
    }

    @Test
    void shouldGetAvailableSeats() throws Exception {
        when(availableSeatsRepository.findAll()).thenReturn(Arrays.asList(seat1, seat2));
        mockMvc.perform(get("/available_seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldGetAvailableSeatsById() throws Exception {
        Long testId = 1L;
        when(availableSeatsRepository.findById(testId)).thenReturn(Optional.of(seat1));
        mockMvc.perform(get("/available_seats/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(seat1.getId()));
    }

    @Test
    void shouldGetEmptyAvailableSeatsById() throws Exception {
        Long testId = 1L;
        when(availableSeatsRepository.findAvailableSeatsBySeanceId(testId)).thenReturn(Arrays.asList(seat1));
        mockMvc.perform(get("/available_seats/seance/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }
}


