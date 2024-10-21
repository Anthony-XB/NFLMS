package com.CEN30241.nflms.service.PlayerServiceTests;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadPlayersTest {

    private PlayerService playerService;
    private String testFile;

    @BeforeEach
    void setUp() {
        playerService = new PlayerService();
        testFile = "loadPlayertest.txt";
    }

    @Test
    @DisplayName("Load Player Test")
    void loadPlayersFromFile() throws IOException {

       try(FileWriter writer = new FileWriter(testFile)){
            writer.write("J.Allen,QB,BUF,PassingYards:4100,PassingTouchdowns:35,Interceptions:0.0\n");
            writer.write("C.McCaffrey,RB,SF,RushingYards:1200.0,RushingTouchdowns:12.0,Receptions:75.0\n");
       }

       playerService.loadPlayersFromFile(testFile);


       List<Player> players = playerService.getPlayers();
       Player firstPlayer = players.get(0);
       Player secondPlayer = players.get(1);
       Stats firstPlayerStats = firstPlayer.getStats();
       Stats secondPlayerStats = secondPlayer.getStats();

//       List<Stats> stats = playerService.getPlayersStatsForComparison(firstPlayer, );
       assertEquals(2, players.size());


       assertEquals("J.Allen", firstPlayer.getName());
       assertEquals("QB", firstPlayer.getPosition());
       assertEquals("BUF", firstPlayer.getTeam());
       ///cant do this because of string and we are retrieving int
       assertEquals(4100, firstPlayerStats.getPassingYards());
       assertEquals(35, firstPlayerStats.getPassingTouchdowns());
       assertEquals(0.0,firstPlayerStats.getInterceptions());

        assertEquals("C.McCaffrey", secondPlayer.getName());
        assertEquals("RB", secondPlayer.getPosition());
        assertEquals("SF", secondPlayer.getTeam());
        ///cant do this because of string and we are retrieving int
        assertEquals(1200, secondPlayerStats.getRushingYards());
        assertEquals(12, secondPlayerStats.getRushingTouchdowns());
        assertEquals(75,secondPlayerStats.getReceptions());

    }


}