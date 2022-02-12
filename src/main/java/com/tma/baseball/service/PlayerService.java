package com.tma.baseball.service;

import com.tma.baseball.exception.BaseballException;
import com.tma.baseball.model.Team;
import com.tma.baseball.repository.PlayerRepo;
import com.tma.baseball.model.Player;
import com.tma.baseball.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo playerRepo;
    private TeamRepo teamRepo;

    public PlayerService(PlayerRepo playerRepo, TeamRepo teamRepo){
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public List<Player> getAllPlayersOfATeam(Long teamId) {
        return playerRepo.findByTeamId(teamId);
    }

    public Player addPlayerToTeam(Long teamId, Player player) {
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if(optionalTeam.isEmpty()) {
            throw new BaseballException("Team Does Not Exist");
        }
        Team selectedTeam = optionalTeam.get();
        if(player.getAge() == null) {
            throw new BaseballException("Please provide an age for the player");
        }
        if(player.getFirstName() == null) {
            throw new BaseballException("Please provide a first name for the player");
        }
        if(player.getLastName() == null) {
            throw new BaseballException("Please provide a last name for the player");
        }
        if(player.getHeight() == null) {
            throw new BaseballException("Please provide a height for the player");
        }
        if(player.getPosition() == null) {
            throw new BaseballException("Please provide a position for the player");
        }
        if(player.getSalary() == null) {
            throw new BaseballException("Please provide a salary for the player");
        }
        if(player.getWeight() == null) {
            throw new BaseballException("Please provide a weight for the player");
        }
        playerRepo.save(player);
        selectedTeam.addPlayer(player);
        teamRepo.save(selectedTeam);
        return player;
    }

}
