package com.tma.baseball.repository;

import com.tma.baseball.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Long> {

    List<Team> findByCityAndMascot(String city, String mascot);

    List<Team> findByCityAndDivision(String city, String division);

    List<Team> findByMascotAndDivision(String mascot, String division);

    List<Team> findByCityAndMascotAndDivision(String city, String mascot, String division);

    List<Team> findByCity(String city);

    List<Team> findByMascot(String mascot);

    List<Team> findByDivision(String division);

    Team findByNameIgnoreCase(String name);

    Team findByName(String name);
}
