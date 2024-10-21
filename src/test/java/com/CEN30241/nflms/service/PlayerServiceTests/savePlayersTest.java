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

class savePlayersTest {

    private PlayerService playerService;

    //input your own txtfile path here
    private final String testFileName = "C:/Users/User/Desktop/test_players.txt";

    @BeforeEach
    void setUp() throws IOException {
        playerService = new PlayerService();


        Files.deleteIfExists(Path.of(testFileName));
        Files.createFile(Path.of(testFileName));
    }

    @Test
    @DisplayName("Test Save Players to File")
    void testSavePlayersToFile() throws IOException {

        Player firstPlayer = new Player("T.Lawrence", "QB", "JAX");
        Stats firstPlayerStats = new Stats(20, 15, 3500, 25, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        firstPlayer.setStats(firstPlayerStats);



        playerService.addPlayer(testFileName, firstPlayer);



        playerService.savePlayersToFile(testFileName);


        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line1 = reader.readLine();
            assertNotNull(line1, "The first line should not be null.");
            assertTrue(line1.contains("T.Lawrence"), "The line should contain the first player's name.");

 } catch (IOException e) {
            fail("Failed to read from the test file: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() throws IOException {

        Files.deleteIfExists(Path.of(testFileName));
    }
}