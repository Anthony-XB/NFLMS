package com.CEN30241.nflms.service.PlayerServiceTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;

import static org.junit.jupiter.api.Assertions.*;

public class addPlayerNegativeTest {

    private PlayerService playerService;
    private final String testFileName = "test_players.txt";

    @BeforeEach
    void setUp() throws IOException {
        playerService = new PlayerService();


        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
    }



    @Test
    void testAddPlayerWithIncompleteStats() throws IOException {
        Player player = new Player("T.Lawrence", "QB", "JAX");
        Stats stats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        player.setStats(stats);


        playerService.addPlayer(testFileName, player);


        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line = reader.readLine();
            assertNotNull(line, "The player data should be saved to the file.");
            assertTrue(line.contains("T.Lawrence"), "The line should contain the player's name.");
            assertTrue(line.contains("QB"), "The line should contain the player's position.");
            assertTrue(line.contains("JAX"), "The line should contain the player's team.");

            assertTrue(line.contains("PassingAttempts:0"), "The line should contain passing attempts.");
        }
    }
}
