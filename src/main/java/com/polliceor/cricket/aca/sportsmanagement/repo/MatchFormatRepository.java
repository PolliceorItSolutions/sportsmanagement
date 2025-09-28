package com.polliceor.cricket.aca.sportsmanagement.repo;

import com.polliceor.cricket.aca.sportsmanagement.domain.MatchFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchFormatRepository extends JpaRepository<MatchFormat, Long> {
    Optional<MatchFormat> findByName(String name);
}
