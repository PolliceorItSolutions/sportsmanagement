package com.polliceor.cricket.aca.sportsmanagement.api.dto;


import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String idToken;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }



}
