package com.polliceor.cricket.aca.sportsmanagement.api.dto;


import jakarta.validation.constraints.Size;

public class RegisterRequest {

    private String idToken; // Firebase ID token from frontend

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
