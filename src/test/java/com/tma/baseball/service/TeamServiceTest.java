package com.tma.baseball.service;

import com.tma.baseball.model.Team;
import com.tma.baseball.repository.TeamRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    TeamService teamService;

    TeamRepo mockTeamRepo;

    List<Team> teams = new ArrayList<>();

    Team team1 = new Team();
    Team team2 = new Team();
    Team team3 = new Team();
    Team team4 = new Team();

    @BeforeEach
    public void setup() {
        team1.setCity("Dallas");
        team1.setDivision("Central");
        team1.setMascot("Eagle");

        team2.setCity("columbus");
        team2.setDivision("East");
        team2.setMascot("Bears");

        team3.setCity("Dallas");
        team3.setDivision("Central");
        team3.setMascot("Otters");

        team4.setCity("Jackson");
        team4.setDivision("central");
        team4.setMascot("Eagle");

        mockTeamRepo = mock(TeamRepo.class);
        teamService = new TeamService(mockTeamRepo);
        teams = Arrays.asList(team1, team2, team3, team4);
    }

    @Test
    void getAllTeamsWithNoQueries() {
        when(mockTeamRepo.findAll()).thenReturn(teams);
        assertEquals(4, teamService.getAllTeams(null, null, null).size());
        verify(mockTeamRepo).findAll();
    }

    @Test
    void getAllTeamsWithCityQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        when(mockTeamRepo.findByCityIgnoreCase("dallas")).thenReturn(newTeams);
        assertEquals(1, teamService.getAllTeams("dallas", null, null).size());
        verify(mockTeamRepo).findByCityIgnoreCase("dallas");
    }

    @Test
    void getAllTeamsWithMascotQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        when(mockTeamRepo.findByMascotIgnoreCase("Eagle")).thenReturn(newTeams);
        assertEquals(1, teamService.getAllTeams(null,  "Eagle", null).size());
        verify(mockTeamRepo).findByMascotIgnoreCase("Eagle");
    }

    @Test
    void getAllTeamsWithDivisionQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        when(mockTeamRepo.findByDivisionIgnoreCase("Central")).thenReturn(newTeams);
        assertEquals(1, teamService.getAllTeams(null,  null, "Central").size());
        verify(mockTeamRepo).findByDivisionIgnoreCase("Central");
    }

    @Test
    void getAllTeamsWithDivisionAndCityQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        newTeams.add(team3);
        when(mockTeamRepo.findByCityIgnoreCaseAndDivisionIgnoreCase("Dallas", "Central")).thenReturn(newTeams);
        assertEquals(2, teamService.getAllTeams("Dallas", null, "Central").size());
        verify(mockTeamRepo).findByCityIgnoreCaseAndDivisionIgnoreCase("Dallas", "Central");
    }

    @Test
    void getAllTeamsWithDivisionAndMascotQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        newTeams.add(team4);
        when(mockTeamRepo.findByMascotIgnoreCaseAndDivisionIgnoreCase("eagle", "Central")).thenReturn(newTeams);
        assertEquals(2, teamService.getAllTeams(null, "eagle", "Central").size());
        verify(mockTeamRepo).findByMascotIgnoreCaseAndDivisionIgnoreCase("eagle", "Central");
    }

    @Test
    void getAllTeamsWithCityAndMascotQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team3);
        when(mockTeamRepo.findByCityIgnoreCaseAndMascotIgnoreCase("dallas", "eagle")).thenReturn(newTeams);
        assertEquals(1, teamService.getAllTeams("dallas", "eagle", null).size());
        verify(mockTeamRepo).findByCityIgnoreCaseAndMascotIgnoreCase("dallas", "eagle");
    }

    @Test
    void getAllTeamsWithCityAndMascotAndDivisionQuery() {
        List<Team> newTeams = new ArrayList<>();
        newTeams.add(team3);
        when(mockTeamRepo.findByCityIgnoreCaseAndMascotIgnoreCaseAndDivisionIgnoreCase("dallas", "eagle", "central")).thenReturn(newTeams);
        assertEquals(1, teamService.getAllTeams("dallas", "eagle", "central").size());
        verify(mockTeamRepo).findByCityIgnoreCaseAndMascotIgnoreCaseAndDivisionIgnoreCase("dallas", "eagle", "central");
    }
}