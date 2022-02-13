package com.tma.baseball;

import com.tma.baseball.controller.TeamController;
import com.tma.baseball.model.Team;
import com.tma.baseball.repository.PlayerRepo;
import com.tma.baseball.repository.TeamRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BaseballApplicationTests {

	@Autowired
	TeamRepo teamRepo;

	@Autowired
	PlayerRepo playerRepo;

	@Autowired
	TeamController teamController;

	List<Team> teams = new ArrayList<>();

	Team team1 = new Team();
	Team team2 = new Team();
	Team team3 = new Team();
	Team team4 = new Team();

	@BeforeEach
	public void setup(){
		teamRepo.deleteAll();
		playerRepo.deleteAll();

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

		teams = Arrays.asList(team1, team2, team3, team4);
		teamRepo.saveAll(teams);
	}

	@Test
	public void getAllTeamsWithNoQueries(){
		teamController.getAllTeamDetails(null, null, null);
		assertEquals(4, teamController.getAllTeamDetails(null, null, null).size());
	}

	@Test
	void getAllTeamsWithCityQuery() {
		teamController.getAllTeamDetails("dallas", null, null);
		assertEquals(2, teamController.getAllTeamDetails("dallas", null, null).size());
	}

	@Test
	void getAllTeamsWithMascotQuery() {
		teamController.getAllTeamDetails(null,  "Eagle", null);
		assertEquals(2, teamController.getAllTeamDetails(null,  "Eagle", null).size());
	}

	@Test
	void getAllTeamsWithDivisionQuery() {
		teamController.getAllTeamDetails(null,  null, "Central");
		assertEquals(3, teamController.getAllTeamDetails(null,  null, "Central").size());
	}

	@Test
	void getAllTeamsWithDivisionAndCityQuery() {
		teamController.getAllTeamDetails("Dallas", null, "Central");
		assertEquals(2, teamController.getAllTeamDetails("Dallas", null, "Central").size());
	}

	@Test
	void getAllTeamsWithDivisionAndMascotQuery() {
		teamController.getAllTeamDetails(null, "eagle", "Central");
		assertEquals(2, teamController.getAllTeamDetails(null, "eagle", "Central").size());
	}

	@Test
	void getAllTeamsWithCityAndMascotQuery() {
		teamController.getAllTeamDetails("Dallas", "Eagle", null);
		assertEquals(1, teamController.getAllTeamDetails("Dallas", "Eagle", null).size());
	}

	@Test
	void getAllTeamsWithCityAndMascotAndDivisionQuery() {
		teamController.getAllTeamDetails("dallas", "eagle", "central");
		assertEquals(1, teamController.getAllTeamDetails("dallas", "eagle", "central").size());
	}

}
