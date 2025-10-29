package com.polliceor.cricket.aca.sportsmanagement.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
public class CreateGroundRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    private Integer capacity;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime earliestStart;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime latestEnd;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalTime getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(LocalTime earliestStart) {
        this.earliestStart = earliestStart;
    }

    public LocalTime getLatestEnd() {
        return latestEnd;
    }

    public void setLatestEnd(LocalTime latestEnd) {
        this.latestEnd = latestEnd;
    }
}
