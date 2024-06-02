package com.example.cinema;

import com.example.cinema.movie.Movie;
import com.example.cinema.movie.MovieRepository;
import com.example.cinema.movie.MovieController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTests {

    private MockMvc mockMvc;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieController movieController;

    private Movie movie1;
    private Movie movie2;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

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

    }

    @Test
    public void shouldReturnAllMovies() throws Exception {
        given(movieRepository.findAll()).willReturn(Arrays.asList(movie1, movie2));

        mockMvc.perform(get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(movie1.getId()))
                .andExpect(jsonPath("$[0].movieName").value(movie1.getMovieName()))
                .andExpect(jsonPath("$[1].id").value(movie2.getId()))
                .andExpect(jsonPath("$[1].movieName").value(movie2.getMovieName()))
                .andDo(print());
    }

    @Test
    public void shouldReturnMovieById() throws Exception {
        given(movieRepository.findById(1L)).willReturn(Optional.of(movie1));
        mockMvc.perform(get("/movies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie1.getId()))
                .andExpect(jsonPath("$.movieName").value(movie1.getMovieName()))
                .andDo(print());
    }
}
