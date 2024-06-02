package com.example.cinema;

import com.example.cinema.movie.Movie;
import com.example.cinema.movie.MovieRepository;
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
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    public void setup() {
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setMovieName("The Shawshank Redemption");
        movie1.setMovieDescription("SomeDescription");
        movie1.setMovieRunningTime("1000min");
        movie1.setMovieThumbnailUrl("SomeUrl");
        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setMovieName("The Godfather");
        movie2.setMovieDescription("SomeDescription");
        movie2.setMovieRunningTime("1000min");
        movie2.setMovieThumbnailUrl("SomeUrl");

        given(movieRepository.findAll()).willReturn(Arrays.asList(movie1, movie2));
        given(movieRepository.findById(1L)).willReturn(Optional.of(movie1));
        given(movieRepository.findById(2L)).willReturn(Optional.of(movie2));
    }

    @Test
    public void shouldReturnAllMovies() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(movie1.getId()))
                .andExpect(jsonPath("$[0].movieName").value(movie1.getMovieName()))
                .andExpect(jsonPath("$[1].id").value(movie2.getId()))
                .andExpect(jsonPath("$[1].movieName").value(movie2.getMovieName()))
                .andDo(print());
    }

    @Test
    public void shouldReturnMovieId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie1.getId()))
                .andExpect(jsonPath("$.movieName").value(movie1.getMovieName()))
                .andDo(print());
    }


}
