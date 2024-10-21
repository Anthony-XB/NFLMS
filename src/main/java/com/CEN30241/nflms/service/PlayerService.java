package com.CEN30241.nflms.service;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {

    private List<Player> players = new ArrayList<>();

    public void loadPlayersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] playerData = line.split(",");
                if (playerData.length >= 3) {
                    String name = playerData[0];
                    String position = playerData[1];
                    String team = playerData[2];
                    Player player = new Player(name, position, team);

                    Stats stats = player.getStats();


                    for (int i = 3; i < playerData.length; i++) {
                        String[] statPair = playerData[i].split(":");
                        String statKey = statPair[0];
                        double statValue = Double.parseDouble(statPair[1]);


                        switch (statKey) {
                            case "PassingAttempts":
                                stats.setPassingAttempts(statValue);
                                break;
                            case "PassingCompletions":
                                stats.setPassingCompletions(statValue);
                                break;
                            case "PassingYards":
                                stats.setPassingYards(statValue);
                                break;
                            case "PassingTouchdowns":
                                stats.setPassingTouchdowns(statValue);
                                break;
                            case "Interceptions":
                                stats.setInterceptions(statValue);
                                break;
                            case "RushingYards":
                                stats.setRushingYards(statValue);
                                break;
                            case "RushingTouchdowns":
                                stats.setRushingTouchdowns(statValue);
                                break;
                            case "ReceivingYards":
                                stats.setReceivingYards(statValue);
                                break;
                            case "ReceivingTouchdowns":
                                stats.setReceivingTouchdowns(statValue);
                                break;
                            case "Receptions":
                                stats.setReceptions((int) statValue);
                                break;
                            case "FieldGoalsMade":
                                stats.setFieldGoalsMade((int) statValue);
                                break;
                            case "ExtraPointsMade":
                                stats.setExtraPointsMade((int) statValue);
                                break;
                            case "FieldGoalPercentage":
                                stats.setFieldGoalPercentage(statValue);
                                break;
                        }
                    }
                    players.add(player);
                }
            }
            System.out.println("Players loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading players from file: " + e.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void savePlayersToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : players) {
                String playerData = player.getName() + "," + player.getPosition() + "," + player.getTeam();


                Stats stats = player.getStats();


                switch (player.getPosition()) {
                    case "QB":
                        playerData += ",PassingAttempts:" + stats.getPassingAttempts()
                                + ",PassingCompletions:" + stats.getPassingCompletions()
                                + ",PassingYards:" + stats.getPassingYards()
                                + ",PassingTouchdowns:" + stats.getPassingTouchdowns()
                                + ",Interceptions:" + stats.getInterceptions()
                                + ",RushingAttempts:" + stats.getRushingAttempts()
                                + ",RushingYards:" + stats.getRushingYards()
                                + ",RushingTouchdowns:" + stats.getRushingTouchdowns();
                        break;
                    case "RB":
                        playerData += ",RushingAttempts:" + stats.getRushingAttempts()
                                + ",RushingYards:" + stats.getRushingYards()
                                + ",RushingTouchdowns:" + stats.getRushingTouchdowns()
                                + ",Receptions:" + stats.getReceptions()
                                + ",ReceivingYards:" + stats.getReceivingYards()
                                + ",ReceivingTouchdowns:" + stats.getReceivingTouchdowns();
                        break;

                    case "WR":
                    case "TE":
                        playerData += ",ReceivingYards:" + stats.getReceivingYards()
                                + ",ReceivingTouchdowns:" + stats.getReceivingTouchdowns()
                                + ",Receptions:" + stats.getReceptions();
                        break;
                    case "K":
                        playerData += ",FieldGoalsMade:" + stats.getFieldGoalsMade()
                                + ",ExtraPointsMade:" + stats.getExtraPointsMade()
                                + ",FieldGoalPercentage:" + stats.getFieldGoalPercentage();
                        break;
                }


                writer.write(playerData);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving players to file: " + e.getMessage());
        }
    }

    public Optional<Player> getPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equalsIgnoreCase(name))
                .findFirst();
    }


    public void addPlayer(String fileName, Player player) {

        StringBuilder playerData = new StringBuilder();
        playerData.append(player.getName()).append(",")
                .append(player.getPosition()).append(",")
                .append(player.getTeam());


        Stats stats = player.getStats();
        switch (player.getPosition()) {
            case "QB":
                playerData.append(",PassingAttempts:").append(stats.getPassingAttempts())
                        .append(",PassingCompletions:").append(stats.getPassingCompletions())
                        .append(",PassingYards:").append(stats.getPassingYards())
                        .append(",PassingTouchdowns:").append(stats.getPassingTouchdowns())
                        .append(",Interceptions:").append(stats.getInterceptions());
                break;
            case "RB":
                playerData.append(",RushingAttempts:").append(stats.getRushingAttempts())
                        .append(",RushingYards:").append(stats.getRushingYards())
                        .append(",RushingTouchdowns:").append(stats.getRushingTouchdowns())
                        .append(",Receptions:").append(stats.getReceptions());
                break;
            case "WR":
            case "TE":
                playerData.append(",ReceivingYards:").append(stats.getReceivingYards())
                        .append(",ReceivingTouchdowns:").append(stats.getReceivingTouchdowns())
                        .append(",Receptions:").append(stats.getReceptions());
                break;
            case "K":
                playerData.append(",FieldGoalsMade:").append(stats.getFieldGoalsMade())
                        .append(",ExtraPointsMade:").append(stats.getExtraPointsMade())
                        .append(",FieldGoalPercentage:").append(stats.getFieldGoalPercentage());
                break;

        }

        // Append the new player's data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // 'true' to append
            writer.write(playerData.toString());
            writer.newLine(); // Write the player data followed by a new line
            System.out.println(player.getName() + " has been added to the list."); // Optional confirmation message
        } catch (IOException e) {
            System.out.println("Error saving player to file: " + e.getMessage());
        }

        loadPlayersFromFile(fileName);

    }



    public void removePlayer(String fileName, String playerName) {


        List<Player> playersToKeep = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] playerData = line.split(",");
                String name = playerData[0].trim();
                Player player = new Player(name, playerData[1].trim(), playerData[2].trim());

                if (!name.equalsIgnoreCase(playerName)) {
                    playersToKeep.add(player);
                } else {
                    System.out.println("Removing player: " + name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading players from file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : playersToKeep) {
                writer.write(player.getName() + "," + player.getPosition() + "," + player.getTeam());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing players to file: " + e.getMessage());
        }

        loadPlayersFromFile(fileName);
    }






    public List<Stats> getStats(String playerName){
        List<Stats> statList = new ArrayList<>();

        Optional<Player> player = getPlayerByName(playerName);

        player.ifPresent(p -> statList.add(p.getStats()));

        return statList;
    }

    public List<Stats> getPlayersStatsForComparison(String player1Name, String player2Name) {
        List<Stats> statsList = new ArrayList<>();

        Optional<Player> player1 = getPlayerByName(player1Name);
        Optional<Player> player2 = getPlayerByName(player2Name);


        player1.ifPresent(p -> statsList.add(p.getStats()));
        player2.ifPresent(p -> statsList.add(p.getStats()));

        return statsList;
    }

}
