package com.polliceor.cricket.aca.sportsmanagement.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class HealthController {

    @GetMapping({"/health", "/_health"})
    public Map<String, Object> health() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        body.put("timestamp", OffsetDateTime.now().toString());
        return body;
    }

    // Some load balancers check root path by default. Return 200 OK quickly.
    @GetMapping("/")
    public ResponseEntity<Void> root() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
