package com.polliceor.cricket.aca.sportsmanagement.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class CreateGroundRequest {

    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime earliestStart;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime latestEnd;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalTime getEarliestStart() { return earliestStart; }
    public void setEarliestStart(LocalTime earliestStart) { this.earliestStart = earliestStart; }

    public LocalTime getLatestEnd() { return latestEnd; }
    public void setLatestEnd(LocalTime latestEnd) { this.latestEnd = latestEnd; }
}
