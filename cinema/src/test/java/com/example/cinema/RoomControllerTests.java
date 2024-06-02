package com.example.cinema;

import com.example.cinema.room.Room;
import com.example.cinema.room.RoomController;
import com.example.cinema.room.RoomRepository;
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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTests {

    private MockMvc mockMvc;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomController roomController;

    private Room room1;
    private Room room2;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();

        room1 = new Room();
        room1.setId(1L);
        room1.setRoomName("SomeName");

        room2 = new Room();
        room2.setId(2L);
        room2.setRoomName("SomeName");
    }
    @Test
    public void shouldReturnAllRooms() throws Exception {
        given(roomRepository.findAll()).willReturn(Arrays.asList(room1, room2));
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
        given(roomRepository.findById(1L)).willReturn(Optional.of(room1));
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(room1.getId()))
                .andExpect(jsonPath("$.roomName").value(room1.getRoomName()))
                .andDo(print());
    }
}
