package com.polliceor.cricket.aca.sportsmanagement.api;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.CreateGroundRequest;
import com.polliceor.cricket.aca.sportsmanagement.domain.Ground;
import com.polliceor.cricket.aca.sportsmanagement.repo.GroundRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/grounds")
public class GroundController {

    private final GroundRepository groundRepository;

    public GroundController(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ground createGround(@Valid @RequestBody CreateGroundRequest req) {
        groundRepository.findByName(req.getName()).ifPresent(g -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ground with name already exists");
        });
        if (req.getEarliestStart().isAfter(req.getLatestEnd()) || req.getEarliestStart().equals(req.getLatestEnd())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "earliestStart must be before latestEnd");
        }
        Ground ground = new Ground();
        ground.setName(req.getName());
        ground.setEarliestStart(req.getEarliestStart());
        ground.setLatestEnd(req.getLatestEnd());
        return groundRepository.save(ground);
    }
}
