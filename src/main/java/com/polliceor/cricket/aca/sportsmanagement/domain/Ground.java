package com.polliceor.cricket.aca.sportsmanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @NotBlank
    @Column(nullable = false)
    private String location;

    @Column
    private Integer capacity;

    @Column(nullable = false)
    private LocalTime earliestStart;

    @Column(nullable = false)
    private LocalTime latestEnd;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Ground() {}

    public Ground(String name, String location, LocalTime earliestStart, LocalTime latestEnd) {
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
