package com.tma.baseball.controller;

import com.tma.baseball.model.Team;
import com.tma.baseball.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TeamControllerTest {

    TeamController teamController;

    TeamService mockTeamService;

    List<Team> teams = new ArrayList<>();

    Team team1 = new Team();
    Team team2 = new Team();

    @BeforeEach
    public void setup() {
        mockTeamService = mock(TeamService.class);
        teamController = new TeamController(mockTeamService);
        teams = Arrays.asList(new Team(), new Team(), new Team());
    }

    @Test
    void getAllTeamDetails() {
        when(mockTeamService.getAllTeams(null,null,null)).thenReturn(teams);
        assertEquals(3, teamController.getAllTeamDetails(null, null, null).size());
        verify(mockTeamService).getAllTeams(null,null,null);
    }

    @Test
    void getTeamDetails() {
        team1.setCity("dallas");
        team1.setDivision("Central");
        team1.setMascot("Eagle");
        when(mockTeamService.getTeam(1L)).thenReturn(team1);
        assertEquals(team1, teamController.getTeamDetails(1L));
        verify(mockTeamService).getTeam(1L);
    }

    @Test
    void updateTeamDetails() {
        team1.setCity("dallas");
        team1.setDivision("Central");
        team1.setMascot("Eagle");
        team2.setCity("Mississippi");
        team2.setDivision("Central");
        team2.setMascot("Eagle");
        when(mockTeamService.updateTeam(1L, team2)).thenReturn(team2);
        assertEquals(team2, teamController.updateTeamDetails(1L, team2));
        verify(mockTeamService).updateTeam(1L, team2);
    }

    @Test
    void addTeam() {
        team1.setCity("dallas");
        team1.setDivision("Central");
        team1.setMascot("Eagle");
        when(mockTeamService.addTeam(team1)).thenReturn(team1);
        assertEquals(team1, teamController.addTeam(team1));
        verify(mockTeamService).addTeam(team1);
    }
}