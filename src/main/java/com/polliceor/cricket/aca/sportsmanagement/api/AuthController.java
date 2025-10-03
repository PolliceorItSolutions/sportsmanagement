package com.polliceor.cricket.aca.sportsmanagement.api;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.*;
import com.polliceor.cricket.aca.sportsmanagement.domain.User;
import com.polliceor.cricket.aca.sportsmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest request) {
        User user = userService.register(request);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.authenticate(request.getUsername(), request.getPassword());
    }
}
