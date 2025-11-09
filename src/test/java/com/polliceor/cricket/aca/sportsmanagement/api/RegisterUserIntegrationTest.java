package com.polliceor.cricket.aca.sportsmanagement.api;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.RegisterRequest;
import com.polliceor.cricket.aca.sportsmanagement.api.dto.UserResponse;
import com.polliceor.cricket.aca.sportsmanagement.util.FirebaseTestUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterUserIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    private String firebaseUid;

    private String idToken;

    private String email;


    private String url(String path) {
        return "http://localhost:" + port + "/sportsmanagementservices" + path;
    }

    private HttpEntity<RegisterRequest> json(RegisterRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(req, headers);
    }

    @BeforeAll
    void createFirebaseUser() throws Exception {
        // Create unique test user
        email = "test_" + UUID.randomUUID().toString().substring(0, 6) + "@example.com";
        String password = "StrongPass1!";

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setEmailVerified(true);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        firebaseUid = userRecord.getUid();

        // Sign in via REST to get an ID token (real Firebase endpoint)
        idToken = FirebaseTestUtil.signInAndGetIdToken(email, password);
    }

    @AfterAll
    void deleteFirebaseUser() throws Exception {
        if (firebaseUid != null) {
            FirebaseAuth.getInstance().deleteUser(firebaseUid);
        }
    }

    @Test
    void registerUser_returns201_andUserResponse() {
        RegisterRequest req = new RegisterRequest();
        req.setFirstName("Alice");
        req.setLastName("Test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(idToken);

        HttpEntity<RegisterRequest> entity = new HttpEntity<>(req, headers);

        ResponseEntity<UserResponse> response = restTemplate.postForEntity(
                url("/api/auth/register"), entity, UserResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmail()).isEqualTo(email);
    }

    /*
    @Test
    void registeringDuplicateEmail_returns409Conflict() throws FirebaseAuthException {
        String email = "bob_" + UUID.randomUUID().toString().substring(0, 5) + "@example.com";

        RegisterRequest req = new RegisterRequest();
        req.setEmail(email);
        req.setFirstName("Bob");
        req.setLastName("Test");

        String idToken = FirebaseAuth.getInstance().createCustomToken(firebaseUid);
        req.setIdToken(idToken);

        // First registration succeeds
        ResponseEntity<UserResponse> first = restTemplate.postForEntity(
                url("/api/auth/register"), json(req), UserResponse.class);
        assertThat(first.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Duplicate registration
        RegisterRequest dup = new RegisterRequest();
        dup.setEmail(email);
        dup.setFirstName("Bob2");
        dup.setLastName("Test2");
        dup.setIdToken(idToken);

        ResponseEntity<String> dupResp = restTemplate.postForEntity(
                url("/api/auth/register"), json(dup), String.class);
        assertThat(dupResp.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    } */
}
