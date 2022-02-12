package com.tma.baseball.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String name;

    private String city;

    private String mascot;

    private String division;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "team_id")
    private List<Player> roster = new ArrayList<>();

    public void addPlayer(Player player) {
        roster.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        roster.remove(player);
        player.setTeam(null);
    }

}
