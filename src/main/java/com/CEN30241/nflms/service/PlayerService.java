package com.CEN30241.nflms.service;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;


public class PlayerService {

    private List<Player> players = new ArrayList<>();





        public List<Player> getAllPlayersFromDatabase() {
            List<Player> players = new ArrayList<>();
            String query = "SELECT * FROM players";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String position = resultSet.getString("position");
                    String team = resultSet.getString("team");
                    String statsJson = resultSet.getString("stats");

                    Player player = new Player(name, position, team);
                    player.setStats(Stats.fromJson(statsJson));
                    players.add(player);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return players;
        }




        public void addPlayerToDatabase(Player player) {
            String query = "INSERT INTO players (name, position, team, stats) VALUES (?, ?, ?, ?)";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, player.getName());
                statement.setString(2, player.getPosition());
                statement.setString(3, player.getTeam());
                statement.setString(4, player.getStats().toJson());

                statement.executeUpdate();
                System.out.println("Player added to the database: " + player.getName());

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error adding player to the database: " + e.getMessage());
            }
        }

        public boolean removePlayerFromDatabase(String playerName) {
            String query = "DELETE FROM players WHERE name = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, playerName);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Player removed from the database: " + playerName);
                } else {
                    System.out.println("Player not found: " + playerName);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error removing player from the database: " + e.getMessage());
            }
            return false;
        }


        public Optional<Player> getPlayerByName(String name) {
            String query = "SELECT * FROM players WHERE name = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String position = resultSet.getString("position");
                    String team = resultSet.getString("team");
                    String statsJson = resultSet.getString("stats");

                    Player player = new Player(name, position, team);
                    player.setStats(Stats.fromJson(statsJson));
                    return Optional.of(player);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }






        public List<Stats> getStats(String playerName) {
            List<Stats> statList = new ArrayList<>();
            String query = "SELECT stats FROM players WHERE name = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, playerName);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String statsJson = resultSet.getString("stats");
                    Stats stats = Stats.fromJson(statsJson);
                    statList.add(stats);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error fetching stats for player: " + playerName);
            }

            return statList;
        }

    public List<Stats> getPlayersStatsForComparison(String player1Name, String player2Name) {
        List<Stats> statsList = new ArrayList<>();

        String query = "SELECT name, stats FROM players WHERE LOWER(TRIM(name)) IN (?, ?) ORDER BY CASE WHEN LOWER(TRIM(name)) = ? THEN 0 ELSE 1 END";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            player1Name = player1Name.trim().toLowerCase();
            player2Name = player2Name.trim().toLowerCase();

            System.out.println("Searching for player stats with names: " + player1Name + ", " + player2Name);


            statement.setString(1, player1Name);
            statement.setString(2, player2Name);

            statement.setString(3, player1Name);

            ResultSet resultSet = statement.executeQuery();


            System.out.println("Query executed. Checking results...");

            while (resultSet.next()) {
                String dbName = resultSet.getString("name").trim().toLowerCase();
                String statsJson = resultSet.getString("stats");

                System.out.println("Found player in DB: " + dbName);
                System.out.println("Stats JSON: " + statsJson);

                try {
                    Stats stats = Stats.fromJson(statsJson);
                    if (stats != null) {
                        statsList.add(stats);
                        System.out.println("Stats added for player: " + dbName);
                    } else {
                        System.out.println("Stats parsing failed for player: " + dbName);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing stats for player: " + dbName);
                    e.printStackTrace();
                }
            }

            // More detailed debug output
            System.out.println("Final stats list size: " + statsList.size());
            System.out.println("Expected players: " + player1Name + ", " + player2Name);
            System.out.println("Found players: " +
                    statsList.stream()
                            .map(s -> s.toString())
                            .collect(Collectors.joining(", ")));

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error fetching stats for players: " + player1Name + " and " + player2Name);
        }

        return statsList;
    }
    public class DatabaseConnection {
        private static String URL;
        private static String USER;
        private static String PASSWORD;

        static {
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                Properties properties = new Properties();
                properties.load(fis);
                URL = properties.getProperty("db.url");
                USER = System.getenv("DB_USER"); // Read from environment variable
                PASSWORD = System.getenv("DB_PASSWORD"); // Read from environment variable
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading database configuration.");
            }
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

}
