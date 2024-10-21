package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class addplayerstest {

    private PlayerService playerService;
    private final String testFileName = "test_players.txt";

    @BeforeEach
    void setUp() throws IOException {
        playerService = new PlayerService();

        // Ensure the test file is empty before each test
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
    }

    @Test
    void testAddPlayer() throws IOException {

        Player player = new Player("T.Lawrence", "QB", "JAX");
        Stats stats = new Stats(20, 15, 3500, 25, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        player.setStats(stats);


        playerService.addPlayer(testFileName, player);


        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertNotNull(line, "The player data should be saved to the file.");
            assertTrue(line.contains("T.Lawrence"), "The line should contain the player's name.");
            assertTrue(line.contains("QB"), "The line should contain the player's position.");
            assertTrue(line.contains("JAX"), "The line should contain the player's team.");
            assertTrue(line.contains("PassingAttempts:20"), "The line should contain passing attempts.");
            assertTrue(line.contains("PassingYards:3500"), "The line should contain passing yards.");
            assertTrue(line.contains("PassingTouchdowns:25"), "The line should contain passing touchdowns.");
            assertTrue(line.contains("Interceptions:5"), "The line should contain interceptions.");
        }
    }
}