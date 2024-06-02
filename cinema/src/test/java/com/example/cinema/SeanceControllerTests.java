package com.example.cinema;

import com.example.cinema.seance.Seance;
import com.example.cinema.seance.SeanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SeanceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeanceRepository seanceRepository;

    private Seance seance1, seance2;

    @BeforeEach
    public void setup() {
        seance1 = new Seance();
        seance1.setId(1L);
        seance1.setDateOfSeance(Timestamp.valueOf(LocalDateTime.now()));

        seance2 = new Seance();
        seance2.setId(2L);
        seance2.setDateOfSeance(Timestamp.valueOf(LocalDateTime.now().plusHours(3)));

        given(seanceRepository.findByMovieId(1L)).willReturn(Arrays.asList(seance1, seance2));
        given(seanceRepository.findById(1L)).willReturn(Optional.of(seance1));
        given(seanceRepository.findById(2L)).willReturn(Optional.of(seance2));
    }

    @Test
    public void ShouldReturnAllSeances() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{movieId}/seances", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(seance1.getId()))
                .andExpect(jsonPath("$[1].id").value(seance2.getId()))
                .andDo(print());
    }

    @Test
    public void ShouldReturnSeance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/seances/{seanceId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(seance1.getId()))
                .andDo(print());
    }


}
