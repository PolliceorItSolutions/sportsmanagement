package com.polliceor.cricket.aca.sportsmanagement.api;

import com.polliceor.cricket.aca.sportsmanagement.api.dto.CreateMatchFormatRequest;
import com.polliceor.cricket.aca.sportsmanagement.domain.MatchFormat;
import com.polliceor.cricket.aca.sportsmanagement.repo.MatchFormatRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/match-formats")
public class MatchFormatController {

    private final MatchFormatRepository matchFormatRepository;

    public MatchFormatController(MatchFormatRepository matchFormatRepository) {
        this.matchFormatRepository = matchFormatRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchFormat createMatchFormat(@Valid @RequestBody CreateMatchFormatRequest req) {
        matchFormatRepository.findByName(req.getName()).ifPresent(m -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Match format with name already exists");
        });
        MatchFormat mf = new MatchFormat();
        mf.setName(req.getName());
        mf.setType(req.getType()); // duration will be adjusted based on type
        return matchFormatRepository.save(mf);
    }
}
