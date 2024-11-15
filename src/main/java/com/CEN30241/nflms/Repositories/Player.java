package com.CEN30241.nflms.Repositories;


import lombok.Getter;
import lombok.Setter;

/**
 * The Player class represents an NFL player, including their name, position, team,
 * and associated stats.
 */
@Getter
@Setter
public class Player {
    private String name;
    private String position;
    private String team;
    private Stats stats;

    /**
     * Constructs a new Player instance with the specified name, position, and team.
     * Initializes the player's stats with a new Stats object.
     *
     * @param name     the name of the player.
     * @param position the position of the player.
     * @param team     the team of the player.
     */
    public Player(String name, String position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.stats = new Stats();
    }

}