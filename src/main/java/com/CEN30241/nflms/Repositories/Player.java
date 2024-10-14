package com.CEN30241.nflms.Repositories;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private String position;
    private String team;
    private Stats stats;

    public Player(String name, String position, String team, Stats stats) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.stats = stats;
    }


    public Player(String name, String position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.stats = new Stats();
    }

}