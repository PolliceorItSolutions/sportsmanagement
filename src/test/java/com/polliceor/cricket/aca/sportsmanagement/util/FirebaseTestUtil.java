package com.polliceor.cricket.aca.sportsmanagement.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import io.github.cdimascio.dotenv.Dotenv;


import java.util.Map;

public class FirebaseTestUtil {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String FIREBASE_API_KEY = dotenv.get("FIREBASE_API_KEY");


    public static String signInAndGetIdToken(String email, String password) throws Exception {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + FIREBASE_API_KEY;

        Map<String, Object> body = Map.of(
                "email", email,
                "password", password,
                "returnSecureToken", true
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Firebase sign-in failed: " + resp);
        }

        JsonNode node = new ObjectMapper().readTree(resp.getBody());
        return node.get("idToken").asText();
    }
}
