package com.tma.baseball.repository;

import com.tma.baseball.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
}
