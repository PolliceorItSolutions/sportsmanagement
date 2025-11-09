package com.polliceor.cricket.aca.sportsmanagement.api;

import com.google.firebase.auth.FirebaseAuthException;
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

    // Register is optional in Firebase-based systems.
    // Keep it to store extra profile data like firstName/lastName
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest request,
                                 @RequestHeader(value = "Authorization", required = false) String authorizationHeader
    ) throws FirebaseAuthException {
        String idToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            idToken = authorizationHeader.substring(7);
        }
        User user = userService.register(request, idToken);
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    // Login using Firebase ID token
    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.authenticateWithFirebase(request.getIdToken());
    }
}
