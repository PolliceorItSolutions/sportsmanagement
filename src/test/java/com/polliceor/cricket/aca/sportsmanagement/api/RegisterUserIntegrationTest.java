package com.polliceor.cricket.aca.sportsmanagement.api;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.RegisterRequest;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
class RegisterUserIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    private String url(String path) {
        return "http://localhost:" + port + "/sportsmanagementservices" + path;
    }

    private HttpEntity<RegisterRequest> json(RegisterRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(req, headers);
        
    }

    @Test
    void registerUser_returns201_andUserResponse() {
        RegisterRequest req = new RegisterRequest();
        String uuid = UUID.randomUUID().toString();
        req.setUsername("alice_test"+"_"+uuid.substring(0, 4));
        req.setEmail("alice_" + uuid.substring(0, 4) + "@example.com");
        req.setPassword("StrongPass1!");

        ResponseEntity<UserResponse> response = restTemplate.postForEntity(
                url("/api/auth/register"), json(req), UserResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        UserResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getUsername()).isEqualTo("alice_test"+"_"+uuid.substring(0, 4));
        assertThat(body.getEmail()).isEqualTo("alice_" + uuid.substring(0, 4) + "@example.com");
    }

    @Test
    void registeringDuplicateUsernameOrEmail_returns409Conflict() {
        RegisterRequest req = new RegisterRequest();
        String uuid = UUID.randomUUID().toString();
        String username = "bob_test" + "_" + uuid.substring(0, 4);
        String email = "bob" + "_" + uuid.substring(0,4) + "@example.com";

        req.setUsername(username);
        req.setEmail(email);
        req.setPassword("StrongPass1!");


        // First registration should succeed
        ResponseEntity<UserResponse> first = restTemplate.postForEntity(
                url("/api/auth/register"), json(req), UserResponse.class);
        assertThat(first.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Duplicate username
        RegisterRequest dupUser = new RegisterRequest();
        dupUser.setUsername(username);  // Use same username as original
        dupUser.setEmail("another" + UUID.randomUUID().toString() + "@example.com");  // Different email
        dupUser.setPassword("StrongPass1!");
        ResponseEntity<String> dupUserResp = restTemplate.postForEntity(
                url("/api/auth/register"), json(dupUser), String.class);
        assertThat(dupUserResp.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);

        // Duplicate email
        RegisterRequest dupEmail = new RegisterRequest();
        dupEmail.setUsername("another_user" + UUID.randomUUID().toString());  // Different username
        dupEmail.setEmail(email);  // Use same email as original
        dupEmail.setPassword("StrongPass1!");
        ResponseEntity<String> dupEmailResp = restTemplate.postForEntity(
                url("/api/auth/register"), json(dupEmail), String.class);
        assertThat(dupEmailResp.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

}
