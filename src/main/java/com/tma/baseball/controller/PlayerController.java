package com.tma.baseball.controller;

import com.tma.baseball.model.Player;
import com.tma.baseball.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //An endpoint that lists all players on a team
    @GetMapping("/players")
    public List<Player> getPlayers(@RequestParam Long teamId) {
        return playerService.getAllPlayersOfATeam(teamId);
    }
    //An endpoint to add a player to a team
    @PostMapping("/player")
    public Player addPlayer(@RequestParam Long teamId, @RequestBody Player player) {
        return playerService.addPlayerToTeam(teamId, player);
    }
}
