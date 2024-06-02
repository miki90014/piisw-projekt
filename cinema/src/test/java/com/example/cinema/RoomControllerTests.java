package com.example.cinema;

import com.example.cinema.room.Room;
import com.example.cinema.room.RoomController;
import com.example.cinema.room.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class RoomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepository;

    private Room room1;
    private Room room2;

    @BeforeEach
    public void setup() {
        room1 = new Room();
        room1.setId(1L);
        room1.setRoomName("SomeName");

        room2 = new Room();
        room2.setId(2L);
        room2.setRoomName("SomeName");

        given(roomRepository.findAll()).willReturn(Arrays.asList(room1, room2));
        given(roomRepository.findById(1L)).willReturn(Optional.of(room1));
        given(roomRepository.findById(2L)).willReturn(Optional.of(room2));
    }

    @Test
    public void shouldReturnAllRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(room1.getId()))
                .andExpect(jsonPath("$[0].roomName").value(room1.getRoomName()))
                .andExpect(jsonPath("$[1].id").value(room2.getId()))
                .andExpect(jsonPath("$[1].roomName").value(room2.getRoomName()))
                .andDo(print());
    }
    @Test
    public void shouldGetRoomById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(room1.getId()))
                .andExpect(jsonPath("$.roomName").value(room1.getRoomName()))
                .andDo(print());
    }
}
