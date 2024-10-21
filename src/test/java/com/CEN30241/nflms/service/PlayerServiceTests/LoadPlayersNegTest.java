package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoadPlayersNegTest {

    private PlayerService playerService;
    private String testFile;

    @BeforeEach
    void setUp() {
        playerService = new PlayerService();
        testFile = "loadPlayertest.txt"; // File for positive test case
    }

    @Test
    @DisplayName("Negative Test Load Players from Non-existent File")
    void loadPlayersFromNonExistentFile() {
        // Attempt to load players from a file that does not exist
        playerService.loadPlayersFromFile("non_existent_file.txt");

        // Assert that the players list is empty
        List<Player> players = playerService.getPlayers();
        assertTrue(players.isEmpty(), "The player list should be empty when the file does not exist.");
    }

    @Test
    @DisplayName("Negative Test Load Players from Empty File")
    void loadPlayersFromEmptyFile() throws IOException {
        // Create an empty test file
        Path emptyFilePath = Path.of(testFile);
        Files.createFile(emptyFilePath);

        // Load players from the empty file
        playerService.loadPlayersFromFile(testFile);

        // Assert that the players list is empty
        List<Player> players = playerService.getPlayers();
        assertTrue(players.isEmpty(), "The player list should be empty when the file is empty.");

        // Clean up the empty file after the test
        Files.delete(emptyFilePath);
    }

    @Test
    @DisplayName("Load Player Test")
    void loadPlayersFromFile() throws IOException {
        // Create a test file with player data
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("J.Allen,QB,BUF,PassingYards:4100,PassingTouchdowns:35,Interceptions:0.0\n");
            writer.write("C.McCaffrey,RB,SF,RushingYards:1200.0,RushingTouchdowns:12.0,Receptions:75.0\n");
        }

        // Load players from the test file
        playerService.loadPlayersFromFile(testFile);

        List<Player> players = playerService.getPlayers();
        Player firstPlayer = players.get(0);
        Player secondPlayer = players.get(1);
        Stats firstPlayerStats = firstPlayer.getStats();
        Stats secondPlayerStats = secondPlayer.getStats();

        assertEquals(2, players.size());

        assertEquals("J.Allen", firstPlayer.getName());
        assertEquals("QB", firstPlayer.getPosition());
        assertEquals("BUF", firstPlayer.getTeam());
        assertEquals(4100, firstPlayerStats.getPassingYards());
        assertEquals(35, firstPlayerStats.getPassingTouchdowns());
        assertEquals(0.0, firstPlayerStats.getInterceptions());

        assertEquals("C.McCaffrey", secondPlayer.getName());
        assertEquals("RB", secondPlayer.getPosition());
        assertEquals("SF", secondPlayer.getTeam());
        assertEquals(1200, secondPlayerStats.getRushingYards());
        assertEquals(12, secondPlayerStats.getRushingTouchdowns());
        assertEquals(75, secondPlayerStats.getReceptions());
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test file after each test
        Files.deleteIfExists(Path.of(testFile));
    }

}
