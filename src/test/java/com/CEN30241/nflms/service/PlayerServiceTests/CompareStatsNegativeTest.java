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

public class CompareStatsNegativeTest {

    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerService = new PlayerService();

        Player firstPlayer = new Player("T.Lawrence", "QB", "JAX");
        Stats firstPlayerStats = new Stats(20, 15, 3500, 25, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        firstPlayer.setStats(firstPlayerStats);

        playerService.addPlayer("test_players.txt", firstPlayer);
    }



    @Test
    @DisplayName("Compare Stats w/ Non existent players")
    void testCompareStatsNonExistentPlayers() {

        List<Stats> statsList = playerService.getPlayersStatsForComparison("NonExistentPlayer1", "NonExistentPlayer2");


        assertEquals(0, statsList.size(), "Should retrieve no stats when both players do not exist.");
    }

}
