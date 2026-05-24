package com.helphub.helphub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helphub.helphub.model.User;
import com.helphub.helphub.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        user.setRole("USER");

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginData) {

        List<User> users = userRepository.findByEmail(loginData.getEmail());

        if (users.isEmpty()) {
            return "User not found";
        }

        User user = users.get(0);

        if (!user.getPassword().equals(loginData.getPassword())) {
            return "Wrong password";
        }

        return "Login Successful";
    }
}