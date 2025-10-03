package com.polliceor.cricket.aca.sportsmanagement.api.dto;

public class AuthResponse {
    private Long userId;
    private String username;
    private String email;
    private String token; // simple opaque token (not JWT) for demo use

    public AuthResponse() {}

    public AuthResponse(Long userId, String username, String email, String token) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
