package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;

import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class getPlayersNegativeTest {


    private PlayerService playerService;
    private final String testFileName = "test_players.txt";

    @BeforeEach
    void setUp() {
        playerService = new PlayerService();
    }

    @Test
    @DisplayName("Get Players Negative Test ")
    void testGetPlayersNoPlayers() {

        List<Player> players = playerService.getPlayers();


        assertTrue(players.isEmpty(), "The list should be empty when no players are added.");
    }



}
