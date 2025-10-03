package com.polliceor.cricket.aca.sportsmanagement.service;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.AuthResponse;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.RegisterRequest;
import com.polliceor.cricket.aca.sportsmanagement.domain.User;
import com.polliceor.cricket.aca.sportsmanagement.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRoles(Collections.singleton("USER"));
        return userRepository.save(user);
    }

    public AuthResponse authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        User user = userOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        // Minimal token generation. In production, replace with JWT.
        String token = UUID.randomUUID().toString();
        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail(), token);
    }
}
