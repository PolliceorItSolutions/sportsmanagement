package com.polliceor.cricket.aca.sportsmanagement.dto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseCredentials {
    private String url;
    private String username;
    private String password;
}


