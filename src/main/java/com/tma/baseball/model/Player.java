package com.tma.baseball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @JsonInclude()
    @Transient
    private String fullName;

    private Integer age;

    private String height;

    private Integer weight;

    private String position;

    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;

    @PostLoad
    private void postLoad() {
        this.fullName = firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player )) return false;
        return id != null && id.equals(((Player) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
