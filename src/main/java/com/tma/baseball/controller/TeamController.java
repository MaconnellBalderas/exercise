package com.tma.baseball.controller;

import com.tma.baseball.model.Team;
import com.tma.baseball.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService=teamService;
    }

    //An endpoint to get all teams
    @GetMapping("/teams")
    public List<Team> getAllTeamDetails(@RequestParam(required = false) String city,
                                        @RequestParam(required = false) String mascot,
                                        @RequestParam(required = false) String division) {
        return teamService.getAllTeams(city, mascot, division);
    }
    //An endpoint to get a single team
    @GetMapping("/team")
    public Team getTeamDetails(@RequestParam Long teamId) {
        return teamService.getTeam(teamId);
    }
    //An endpoint to update an existing team
    @PatchMapping("/team")
    public Team updateTeamDetails(@RequestParam Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }
    //An endpoint to add a team
    @PostMapping("/team")
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

}
