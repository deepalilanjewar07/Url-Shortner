package com.example.urlshortner.controller;

import com.example.urlshortner.dto.UserDto;
import com.example.urlshortner.model.User;
import com.example.urlshortner.repository.UserRepository;
import com.example.urlshortner.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody UserDto userDto) {

        try {

            System.out.println("SIGNUP HIT");

            User existing = userRepository.findByUsername(userDto.getUsername());

            if (existing != null) {
                return "Username already exists";
            }

            User user = new User();

            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());

            User savedUser = userRepository.save(user);

            System.out.println("USER SAVED");

            return savedUser;

        } catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();
        }
    }
    // LOGIN + JWT TOKEN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserDto userDto) {

        User user = userRepository.findByUsername(userDto.getUsername());

        Map<String, String> response = new HashMap<>();

        if (user == null || !user.getPassword().equals(userDto.getPassword())) {
            response.put("error", "Invalid credentials");
            return response;
        }

        String token = JwtUtil.generateToken(user.getUsername());

        response.put("token", token);
        response.put("message", "Login successful");

        return response;
    }
}