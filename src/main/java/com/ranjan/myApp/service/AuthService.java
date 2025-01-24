package com.ranjan.myApp.service;


import com.ranjan.myApp.UserRepository.UserRepository;
import com.ranjan.myApp.model.User;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    private UserRepository userRepository;
    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "Registration successful!";
    }

    public String login(User user) {
        String storedPassword = userRepository.findByUsername(user.getUsername()).getPassword();
        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }
}
