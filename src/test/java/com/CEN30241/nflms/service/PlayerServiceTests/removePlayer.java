package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class removePlayer {

    private PlayerService playerService;
    private final String testFileName = "test_players.txt";

    @BeforeEach
    void setUp() throws IOException {
        playerService = new PlayerService();


        Files.deleteIfExists(Path.of(testFileName));
        playerService.addPlayer(testFileName, new Player("T.Lawrence", "QB", "JAX"));
        playerService.addPlayer(testFileName, new Player("T.Hill", "WR", "MIA"));
    }

    @Test
    void testRemovePlayer() throws IOException {

        playerService.removePlayer(testFileName, "T.Lawrence");


        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line;
            boolean playerFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("T.Lawrence")) {
                    playerFound = true;
                    break;
                }
            }
            assertFalse(playerFound, "The player T.Lawrence should have been removed.");
        }
    }
}