package com.polliceor.cricket.aca.sportsmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polliceor.cricket.aca.sportsmanagement.dto.ApiKeys;
import com.polliceor.cricket.aca.sportsmanagement.dto.DatabaseCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecretsService {
    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Autowired(required = false)
    private SecretsManagerClient secretsClient;

    public DatabaseCredentials getDatabaseCredentials() {
        if (!isAwsEnabled()) {
            return DatabaseCredentials.builder()
                    .url(environment.getProperty("spring.datasource.url"))
                    .username(environment.getProperty("spring.datasource.username"))
                    .password(environment.getProperty("spring.datasource.password"))
                    .build();
        }

        try {
            String secretString = getSecret("/sports/db-credentials");
            return objectMapper.readValue(secretString, DatabaseCredentials.class);
        } catch (Exception e) {
            log.error("Error parsing database credentials", e);
            throw new RuntimeException("Error parsing database credentials", e);
        }
    }

    private String getSecret(String secretName) {
        if (!isAwsEnabled()) {
            throw new IllegalStateException("AWS Secrets Manager is not enabled");
        }

        try {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();
            GetSecretValueResponse result = secretsClient.getSecretValue(request);
            return result.secretString();
        } catch (Exception e) {
            log.error("Error retrieving secret: {}", secretName, e);
            throw new RuntimeException("Error retrieving secret", e);
        }
    }

    private boolean isAwsEnabled() {
        return secretsClient != null &&
                Boolean.parseBoolean(environment.getProperty("aws.secrets.enabled", "false"));
    }

    public ApiKeys getApiKeys() {
        if (!isAwsEnabled()) {
            throw new IllegalStateException("AWS Secrets Manager is not enabled");
        }

        try {
            String secretString = getSecret("/sports/api-keys");
            return objectMapper.readValue(secretString, ApiKeys.class);
        } catch (Exception e) {
            log.error("Error parsing API keys", e);
            throw new RuntimeException("Error parsing API keys", e);
        }
    }
}