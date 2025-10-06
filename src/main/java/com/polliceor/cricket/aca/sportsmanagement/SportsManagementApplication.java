package com.polliceor.cricket.aca.sportsmanagement;

import com.polliceor.cricket.aca.sportsmanagement.config.AWSSecretsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AWSSecretsConfig.class)

public class SportsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsManagementApplication.class, args);
    }

}
