package com.polliceor.cricket.aca.sportsmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "match_formats")
public class MatchFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private MatchType type;

    @Column(nullable = false)
    private int durationHours;

    public MatchFormat() {}

    public MatchFormat(String name, MatchType type) {
        this.name = name;
        this.type = type;
        adjustDuration();
    }

    @PrePersist
    @PreUpdate
    public void adjustDuration() {
        if (type == null) {
            this.durationHours = 0;
            return;
        }
        switch (type) {
            case SHORT -> this.durationHours = 3;
            case LONG -> this.durationHours = 6;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MatchType getType() { return type; }
    public void setType(MatchType type) { this.type = type; adjustDuration(); }

    public int getDurationHours() { return durationHours; }
    public void setDurationHours(int durationHours) { this.durationHours = durationHours; }
}
