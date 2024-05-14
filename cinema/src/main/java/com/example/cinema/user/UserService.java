package com.example.cinema.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        UserCinemaAttendant user = userRepository.findByUsername(username);
        return user != null && password.equals(user.getPassword());
    }
}
