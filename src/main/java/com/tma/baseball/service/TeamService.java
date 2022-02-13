package com.tma.baseball.service;

import com.tma.baseball.exception.BaseballException;
import com.tma.baseball.model.Team;
import com.tma.baseball.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    public TeamRepo teamRepo;

    public TeamService(TeamRepo teamRepo){
        this.teamRepo = teamRepo;
    }

    public List<Team> getAllTeams(String city, String mascot, String division) {
        if(city != null && mascot != null && division == null) {
            return teamRepo.findByCityIgnoreCaseAndMascotIgnoreCase(city, mascot);
        } else if(city != null && division != null && mascot == null) {
            return teamRepo.findByCityIgnoreCaseAndDivisionIgnoreCase(city, division);
        } else if(mascot != null && division != null && city == null) {
            return teamRepo.findByMascotIgnoreCaseAndDivisionIgnoreCase(mascot, division);
        } else if(city != null && mascot != null && division != null) {
            return teamRepo.findByCityIgnoreCaseAndMascotIgnoreCaseAndDivisionIgnoreCase(city, mascot, division);
        } else if(city != null && mascot == null && division == null)  {
            return teamRepo.findByCityIgnoreCase(city);
        } else if(mascot != null && city == null && division == null) {
            return teamRepo.findByMascotIgnoreCase(mascot);
        } else if(division != null && city == null && mascot == null) {
            return teamRepo.findByDivisionIgnoreCase(division);
        }
        return teamRepo.findAll();
    }

    public Team getTeam(Long teamId) {
        Optional<Team> selectedTeam = teamRepo.findById(teamId);
        if(selectedTeam.isEmpty()) {
            throw new BaseballException("Team Does Not Exist");
        }
        return selectedTeam.get();
    }

    public Team updateTeam(Long id, Team team) {
        Optional<Team> optionalTeam = teamRepo.findById(id);
        if(optionalTeam.isEmpty()) {
            throw new BaseballException("Team Does Not Exist");
        }
        Team selectedTeam = optionalTeam.get();
        if(team.getCity() != null) {
            selectedTeam.setCity(team.getCity());
        }
        if(team.getDivision() != null) {
            selectedTeam.setDivision(team.getDivision());
        }
        if(team.getMascot() != null) {
            selectedTeam.setMascot(team.getMascot());
        }
        return teamRepo.save(selectedTeam);
    }

    public Team addTeam(Team team) {
        Team selectedTeam = teamRepo.findByNameIgnoreCase(team.getName());
        if(selectedTeam != null) {
            throw new BaseballException("Team Already Exists");
        }
        return teamRepo.save(team);
    }

}
