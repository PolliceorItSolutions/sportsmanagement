package com.polliceor.cricket.aca.sportsmanagement.repo;

import com.polliceor.cricket.aca.sportsmanagement.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
