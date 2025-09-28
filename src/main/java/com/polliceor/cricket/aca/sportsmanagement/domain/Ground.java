package com.polliceor.cricket.aca.sportsmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalTime;

@Entity
@Table(name = "grounds")
public class Ground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalTime earliestStart;

    @Column(nullable = false)
    private LocalTime latestEnd;

    public Ground() {}

    public Ground(String name, LocalTime earliestStart, LocalTime latestEnd) {
        this.name = name;
        this.earliestStart = earliestStart;
        this.latestEnd = latestEnd;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalTime getEarliestStart() { return earliestStart; }
    public void setEarliestStart(LocalTime earliestStart) { this.earliestStart = earliestStart; }

    public LocalTime getLatestEnd() { return latestEnd; }
    public void setLatestEnd(LocalTime latestEnd) { this.latestEnd = latestEnd; }
}
