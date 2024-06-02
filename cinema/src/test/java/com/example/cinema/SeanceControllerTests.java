package com.example.cinema;

import com.example.cinema.seance.Seance;
import com.example.cinema.seance.SeanceController;
import com.example.cinema.seance.SeanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class SeanceControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SeanceRepository seanceRepository;

    @InjectMocks
    private SeanceController seanceController;

    private Seance seance1, seance2;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(seanceController).build();
        seance1 = new Seance();
        seance1.setId(1L);
        seance1.setDateOfSeance(Timestamp.valueOf(LocalDateTime.now()));

        seance2 = new Seance();
        seance2.setId(2L);
        seance2.setDateOfSeance(Timestamp.valueOf(LocalDateTime.now().plusHours(3)));
    }

    @Test
    public void shouldReturnAllSeances() throws Exception {
        given(seanceRepository.findByMovieId(1L)).willReturn(Arrays.asList(seance1, seance2));
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{movieId}/seances", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(seance1.getId()))
                .andExpect(jsonPath("$[1].id").value(seance2.getId()))
                .andDo(print());
    }

    @Test
    public void shouldReturnSeance() throws Exception {
        given(seanceRepository.findById(1L)).willReturn(Optional.of(seance1));
        mockMvc.perform(MockMvcRequestBuilders.get("/seances/{seanceId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(seance1.getId()))
                .andDo(print());
    }
}
