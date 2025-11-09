
package com.polliceor.cricket.aca.sportsmanagement.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.AuthResponse;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.RegisterRequest;
import com.polliceor.cricket.aca.sportsmanagement.domain.User;
import com.polliceor.cricket.aca.sportsmanagement.exception.UserAlreadyExistsException;
import com.polliceor.cricket.aca.sportsmanagement.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        log.info("UserService initialized with repository: {}", userRepository.getClass().getSimpleName());
    }

    public User register(RegisterRequest req, String idToken) throws FirebaseAuthException {
        log.debug("Starting user registration process for request: {}", req);

        // Verify token
        User user = verifyFirebaseTokenAndGetDetails(idToken);
        log.info("Firebase token verified successfully for user with email: {}", user.getEmail());

        // Create user in DB
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setRoles(Set.of("USER"));
        log.debug("User details set: firstName={}, lastName={}, roles={}",
                user.getFirstName(), user.getLastName(), user.getRoles());

        try {
            User savedUser = userRepository.save(user);
            log.info("User successfully registered with ID: {}", savedUser.getId());
            return savedUser;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof ConstraintViolationException constraintEx) {
                String constraintName = constraintEx.getConstraintName();
                if ("users_firebase_uid_key".equalsIgnoreCase(constraintName)) {
                    log.warn("Attempted to register existing user with Firebase UID: {}", user.getFirebaseUid());
                    throw new UserAlreadyExistsException("User already exists with this Firebase UID");
                }
            }
            log.error("Database error during user registration", ex);
            throw ex;
        }
    }

    public AuthResponse authenticateWithFirebase(String idToken) {
        log.debug("Starting Firebase authentication process");

        try {
            User user = verifyFirebaseTokenAndGetDetails(idToken);
            log.info("Firebase token verified for user: {}", user.getEmail());

            User dbUser = userRepository.findByFirebaseUid(user.getFirebaseUid())
                    .orElseThrow(() -> {
                        log.warn("Authentication failed: User not found in database for Firebase UID: {}",
                                user.getFirebaseUid());
                        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
                    });

            AuthResponse response = new AuthResponse(dbUser.getId(), dbUser.getUsername(),
                    dbUser.getEmail(), "FAKE-JWT-TOKEN");
            log.info("Authentication successful for user: {}", dbUser.getEmail());
            return response;

        } catch (FirebaseAuthException e) {
            log.error("Firebase authentication failed", e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    public User verifyFirebaseTokenAndGetDetails(String idToken) throws FirebaseAuthException {
        log.debug("Starting Firebase token verification");

        User user = new User();

        // If running in emulator, skip real verification

            log.debug("Verifying Firebase token in production mode");
            // Production mode: verify token with Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            user.setEmail(decodedToken.getEmail());
            user.setFirstName(decodedToken.getName());
            user.setFirebaseUid(decodedToken.getUid());
            log.info("Firebase token verified successfully for user: {}", user.getEmail());

        return user;
    }
}