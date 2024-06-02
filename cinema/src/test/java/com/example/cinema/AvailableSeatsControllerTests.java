package com.example.cinema;

import com.example.cinema.availableseats.AvailableSeats;
import com.example.cinema.availableseats.AvailableSeatsController;
import com.example.cinema.availableseats.AvailableSeatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvailableSeatsControllerTests {

    private MockMvc mockMvc;

    @Mock
    private AvailableSeatsRepository availableSeatsRepository;

    @InjectMocks
    private AvailableSeatsController availableSeatsController;

    private AvailableSeats seat1;
    private AvailableSeats seat2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(availableSeatsController).build();
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


