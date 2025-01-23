package com.ranjan.myApp.controller;

import com.ranjan.myApp.UserRepository.UserRepository;
import com.ranjan.myApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    // Register a new user
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "Registration successful!";
    }

    // Login a user
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String storedPassword = userRepository.findByUsername(user.getUsername()).getPassword();
        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }
}
