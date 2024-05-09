package com.example.cinema.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCinemaAttendant, Long> {
    UserCinemaAttendant findByUsername(String username);
}
