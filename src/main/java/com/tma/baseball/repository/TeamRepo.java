package com.tma.baseball.repository;

import com.tma.baseball.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Long> {

    List<Team> findByCityIgnoreCaseAndMascotIgnoreCase(String city, String mascot);

    List<Team> findByCityIgnoreCaseAndDivisionIgnoreCase(String city, String division);

    List<Team> findByMascotIgnoreCaseAndDivisionIgnoreCase(String mascot, String division);

    List<Team> findByCityIgnoreCaseAndMascotIgnoreCaseAndDivisionIgnoreCase(String city, String mascot, String division);

    List<Team> findByCityIgnoreCase(String city);

    List<Team> findByMascotIgnoreCase(String mascot);

    List<Team> findByDivisionIgnoreCase(String division);

    Team findByNameIgnoreCase(String name);

}
