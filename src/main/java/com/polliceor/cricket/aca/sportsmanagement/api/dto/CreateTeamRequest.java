package com.polliceor.cricket.aca.sportsmanagement.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateTeamRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String contactEmail;

    private String captain;
    private String viceCaptain;
    private String president;
    private String secretary;
    private String treasurer;

    @NotEmpty
    private List<@NotBlank String> members;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getCaptain() { return captain; }
    public void setCaptain(String captain) { this.captain = captain; }

    public String getViceCaptain() { return viceCaptain; }
    public void setViceCaptain(String viceCaptain) { this.viceCaptain = viceCaptain; }

    public String getPresident() { return president; }
    public void setPresident(String president) { this.president = president; }

    public String getSecretary() { return secretary; }
    public void setSecretary(String secretary) { this.secretary = secretary; }

    public String getTreasurer() { return treasurer; }
    public void setTreasurer(String treasurer) { this.treasurer = treasurer; }

    public List<String> getMembers() { return members; }
    public void setMembers(List<String> members) { this.members = members; }
}
