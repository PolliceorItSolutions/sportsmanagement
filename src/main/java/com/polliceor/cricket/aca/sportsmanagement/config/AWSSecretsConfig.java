package com.polliceor.cricket.aca.sportsmanagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polliceor.cricket.aca.sportsmanagement.dto.DatabaseCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;

import javax.sql.DataSource;
@Slf4j
@Configuration
@ConditionalOnProperty(name = "aws.secrets.enabled", havingValue = "true", matchIfMissing = false)
public class AWSSecretsConfig {

    @Value("${aws.secretsmanager.region}")
    private String awsRegion;

    @Bean
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
                .region(Region.of(awsRegion))
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}