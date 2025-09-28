package com.polliceor.cricket.aca.sportsmanagement.api;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.CreateTeamRequest;
import com.polliceor.cricket.aca.sportsmanagement.domain.Team;
import com.polliceor.cricket.aca.sportsmanagement.repo.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@Valid @RequestBody CreateTeamRequest req) {
        teamRepository.findByName(req.getName()).ifPresent(t -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Team with name already exists");
        });
        Team team = new Team();
        team.setName(req.getName());
        team.setContactEmail(req.getContactEmail());
        team.setCaptain(req.getCaptain());
        team.setViceCaptain(req.getViceCaptain());
        team.setPresident(req.getPresident());
        team.setSecretary(req.getSecretary());
        team.setTreasurer(req.getTreasurer());
        team.setMembers(req.getMembers());
        return teamRepository.save(team);
    }
}
