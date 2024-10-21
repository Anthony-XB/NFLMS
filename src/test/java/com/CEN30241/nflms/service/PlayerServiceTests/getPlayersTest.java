package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class getPlayersTest {

    private PlayerService playerService;
    private final String testFileName = "test_players.txt";

    @BeforeEach
    void setUp() {
        playerService = new PlayerService();

    }

    @Test
    @DisplayName("Test Get Players")
    void getPlayers() {

        Player firstPlayer = new Player("T.Lawrence", "QB", "JAX");
        Player secondPlayer = new Player("T.Hill", "WR", "MIA");


        playerService.addPlayer(testFileName, firstPlayer);
        playerService.addPlayer(testFileName, secondPlayer);


        List<Player> players = playerService.getPlayers();


        assertEquals(2, players.size(), "There should be 2 players in the list.");


        assertTrue(players.contains(firstPlayer), "The list should contain the first player.");
        assertTrue(players.contains(secondPlayer), "The list should contain the second player.");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName)); // Remove test file after each test
    }
}
