package com.polliceor.cricket.aca.sportsmanagement.api.dto;

import com.polliceor.cricket.aca.sportsmanagement.domain.MatchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMatchFormatRequest {

    @NotBlank
    private String name;

    @NotNull
    private MatchType type; // SHORT or LONG

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MatchType getType() { return type; }
    public void setType(MatchType type) { this.type = type; }
}
