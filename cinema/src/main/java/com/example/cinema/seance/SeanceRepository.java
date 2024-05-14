package com.example.cinema.seance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Long> {
    List<Seance> findByMovieId(Long movieId);
}
