package com.polliceor.cricket.aca.sportsmanagement.repo;

import com.polliceor.cricket.aca.sportsmanagement.domain.Ground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroundRepository extends JpaRepository<Ground, Long> {
    Optional<Ground> findByName(String name);
}
