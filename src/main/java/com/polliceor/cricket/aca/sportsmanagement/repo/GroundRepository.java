package com.polliceor.cricket.aca.sportsmanagement.repo;

import com.polliceor.cricket.aca.sportsmanagement.domain.Ground;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Tag(name = "Ground", description = "Ground management API")

public interface GroundRepository extends JpaRepository<Ground, Long> {
    Optional<Ground> findByName(String name);
}
