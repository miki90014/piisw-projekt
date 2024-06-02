package com.example.cinema;

import com.example.cinema.seat.Seat;
import com.example.cinema.seat.SeatRepository;
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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SeatControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatRepository seatRepository;

    private Seat seat1, seat2;

    @BeforeEach
    public void setup() {
        seat1 = new Seat();
        seat1.setId(1L);
        seat1.setSeatRow('A');
        seat1.setNumber(1);

        seat2 = new Seat();
        seat2.setId(2L);
        seat2.setSeatRow('A');
        seat2.setNumber(2);

        given(seatRepository.findByRoomId(1L)).willReturn(Arrays.asList(seat1, seat2));
        given(seatRepository.findById(1L)).willReturn(Optional.of(seat1));
    }

    @Test
    public void ShouldReturnAllSeats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/{roomId}/seats", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(seat1.getId()))
                .andExpect(jsonPath("$[0].seatRow").value(String.valueOf(seat1.getSeatRow())))
                .andExpect(jsonPath("$[0].number").value(seat1.getNumber()))
                .andExpect(jsonPath("$[1].id").value(seat2.getId()))
                .andExpect(jsonPath("$[1].seatRow").value(String.valueOf(seat2.getSeatRow())))
                .andExpect(jsonPath("$[1].number").value(seat2.getNumber()))
                .andDo(print());
    }

    @Test
    public void ShouldReturnSeat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/seats/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(seat1.getId()))
               .andExpect(jsonPath("$.seatRow").value(String.valueOf(seat1.getSeatRow())))
                .andExpect(jsonPath("$.number").value(seat1.getNumber()))
                .andDo(print());
    }


}
