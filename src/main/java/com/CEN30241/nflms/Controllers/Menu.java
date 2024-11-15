package com.CEN30241.nflms.Controllers;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Menu Class provides a GUI for managing NFL players, including adding, viewing,
 * comparing stats, and removing players *
 */
public class Menu {

    private PlayerService playerService = new PlayerService();
    private JTextField playerInput, positionInput, teamInput, removePlayerInput, player1Input, player2Input;
    private JLabel playerDetails, resultLabel, compareResultLabel;
    private JTextField passingYardsInput, passingTouchdownsInput, interceptionsInput, rushingAttemptsInput,
            rushingYardsInput, rushingTouchdownsInput, receptionsInput, receivingYardsInput,
            receivingTouchdownsInput, fGAInput, fGMInput, extraPointsMadeInput, fGPercentageInput;
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    /**
     * Constructor that initializes the main JFrame and sets up the main menu.
     */
    public Menu() {
        frame = new JFrame("Player Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        mainPanel.add(createMainMenu(), "Main Menu");
        mainPanel.add(createAddPlayerPanel(), "Add Player");
        mainPanel.add(createViewPlayersPanel(), "View Players");
        mainPanel.add(createCompareStatsPanel(), "Compare Stats");

        frame.add(mainPanel);
        frame.setVisible(true);
        showMainMenu();
    }

    /**
     * Creates the main menu panel with buttons for navigating to other sections.
     *
     * @return JPanel representing the main menu
     */
    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> cardLayout.show(mainPanel, "Add Player"));

        JButton viewPlayersButton = new JButton("View Players");
        viewPlayersButton.addActionListener(e -> {
            viewPlayers();
            cardLayout.show(mainPanel, "View Players");
        });

        JButton compareStatsButton = new JButton("Compare Stats");
        compareStatsButton.addActionListener(e -> cardLayout.show(mainPanel, "Compare Stats"));

        removePlayerInput = new JTextField("Enter Player Name to Remove");
        JButton removePlayerButton = new JButton("Remove Player");
        removePlayerButton.addActionListener(e -> removePlayer());

        resultLabel = new JLabel();

        panel.add(addPlayerButton);
        panel.add(viewPlayersButton);
        panel.add(compareStatsButton);
        panel.add(removePlayerInput);
        panel.add(removePlayerButton);
        panel.add(resultLabel);
        return panel;
    }

    /**
     * Creates the panel for adding aplayer, including input fields for players details and stats.
     *
     * @return JPanel representing the add player panel.
     */
    private JPanel createAddPlayerPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        playerInput = new JTextField("Enter Player Name");
        positionInput = new JTextField("Enter Player Position");
        teamInput = new JTextField("Enter Player Team");
        passingYardsInput = new JTextField("Passing Yards");
        passingTouchdownsInput = new JTextField("Passing Touchdowns");
        interceptionsInput = new JTextField("Interceptions");
        rushingAttemptsInput = new JTextField("Rushing Attempts");
        rushingYardsInput = new JTextField("Rushing Yards");
        rushingTouchdownsInput = new JTextField("Rushing Touchdowns");
        receptionsInput = new JTextField("Receptions");
        receivingYardsInput = new JTextField("Receiving Yards");
        receivingTouchdownsInput = new JTextField("Receiving Touchdowns");
        fGAInput = new JTextField("Field Goals Attempted");
        fGMInput = new JTextField("Field Goals Made");
        extraPointsMadeInput = new JTextField("Extra Points Made");
        fGPercentageInput = new JTextField("Field Goal Percentage");
        resultLabel = new JLabel();

        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> addPlayer());

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Main Menu"));

        panel.add(playerInput);
        panel.add(positionInput);
        panel.add(teamInput);
        panel.add(passingYardsInput);
        panel.add(passingTouchdownsInput);
        panel.add(interceptionsInput);
        panel.add(rushingAttemptsInput);
        panel.add(rushingYardsInput);
        panel.add(rushingTouchdownsInput);
        panel.add(receptionsInput);
        panel.add(receivingYardsInput);
        panel.add(receivingTouchdownsInput);
        panel.add(fGAInput);
        panel.add(fGMInput);
        panel.add(extraPointsMadeInput);
        panel.add(fGPercentageInput);
        panel.add(addPlayerButton);
        panel.add(resultLabel);
        panel.add(backButton);

        return panel;
    }
    /**
     * Creates the panel for viewing all players.
     *
     * @return JPanel representing the View Players panel.
     */
    private JPanel createViewPlayersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        playerDetails = new JLabel();

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Main Menu"));

        panel.add(playerDetails, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Creates the panel for comparing stats between two players.
     *
     * @return JPanel representing the Compare Stats panel.
     */
    private JPanel createCompareStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        player1Input = new JTextField("Enter Player 1 Name");
        player2Input = new JTextField("Enter Player 2 Name");
        compareResultLabel = new JLabel();

        JButton compareButton = new JButton("Compare Stats");
        compareButton.addActionListener(e -> comparePlayerStats());

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Main Menu"));

        panel.add(player1Input);
        panel.add(player2Input);
        panel.add(compareButton);
        panel.add(compareResultLabel);
        panel.add(backButton);

        return panel;
    }

    /**
     * Shows the main menu by setting the main panel to display the "Main Menu" card.
     */
    public void showMainMenu() {
        cardLayout.show(mainPanel, "Main Menu");
    }

    /**
     * Shows the main menu by setting the main panel to display the "Main Menu" card.
     */
    private void comparePlayerStats() {
        String player1Name = player1Input.getText().trim().toLowerCase();
        String player2Name = player2Input.getText().trim().toLowerCase();

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            compareResultLabel.setText("Please enter both player names.");
            return;
        }

        List<Stats> statsList = playerService.getPlayersStatsForComparison(player1Name, player2Name);

        if (statsList.size() == 2) {
            Stats s1 = statsList.get(0);
            Stats s2 = statsList.get(1);

            StringBuilder resultText = new StringBuilder("<html>");
            resultText.append("Player 1: ").append(player1Name).append(" | Player 2: ").append(player2Name).append("<br>");
            resultText.append("Passing Yards: ").append(s1.getPassingYards()).append(" | ").append(s2.getPassingYards()).append("<br>");
            resultText.append("Passing Touchdowns: ").append(s1.getPassingTouchdowns()).append(" | ").append(s2.getPassingTouchdowns()).append("<br>");
            resultText.append("Interceptions: ").append(s1.getInterceptions()).append(" | ").append(s2.getInterceptions()).append("<br>");
            resultText.append("Rushing Attempts: ").append(s1.getRushingAttempts()).append(" | ").append(s2.getRushingAttempts()).append("<br>");
            resultText.append("Rushing Yards: ").append(s1.getRushingYards()).append(" | ").append(s2.getRushingYards()).append("<br>");
            resultText.append("Rushing Touchdowns: ").append(s1.getRushingTouchdowns()).append(" | ").append(s2.getRushingTouchdowns()).append("<br>");
            resultText.append("Receptions: ").append(s1.getReceptions()).append(" | ").append(s2.getReceptions()).append("<br>");
            resultText.append("Receiving Yards: ").append(s1.getReceivingYards()).append(" | ").append(s2.getReceivingYards()).append("<br>");
            resultText.append("Receiving Touchdowns: ").append(s1.getReceivingTouchdowns()).append(" | ").append(s2.getReceivingTouchdowns()).append("<br>");
            resultText.append("Field Goals Attempted: ").append(s1.getFieldGoalsAttempted()).append(" | ").append(s2.getFieldGoalsAttempted()).append("<br>");
            resultText.append("Field Goals Made: ").append(s1.getFieldGoalsMade()).append(" | ").append(s2.getFieldGoalsMade()).append("<br>");
            resultText.append("Extra Points Made: ").append(s1.getExtraPointsMade()).append(" | ").append(s2.getExtraPointsMade()).append("<br>");
            resultText.append("Field Goal Percentage: ").append(s1.getFieldGoalPercentage()).append(" | ").append(s2.getFieldGoalPercentage()).append("<br>");
            resultText.append("</html>");

            compareResultLabel.setText(resultText.toString());
            compareResultLabel.revalidate();
            compareResultLabel.repaint();

        } else {
            compareResultLabel.setText("Stats not found for one or both players.");
        }
    }

    /**
     * Adds a player to the database based on input fields for name, position, team, and stats.
     */
    private void addPlayer() {
        String name = playerInput.getText().trim();
        String position = positionInput.getText().trim();
        String team = teamInput.getText().trim();

        if (!name.isEmpty() && !position.isEmpty() && !team.isEmpty()) {
            Player player = new Player(name, position, team);
            Stats stats = new Stats();

            try {
                switch (position.toUpperCase()) {
                    case "QB":
                        stats.setPassingYards(Double.parseDouble(passingYardsInput.getText().trim()));
                        stats.setPassingTouchdowns(Double.parseDouble(passingTouchdownsInput.getText().trim()));
                        stats.setInterceptions(Double.parseDouble(interceptionsInput.getText().trim()));
                        stats.setRushingAttempts(Double.parseDouble(rushingAttemptsInput.getText().trim()));
                        stats.setRushingYards(Double.parseDouble(rushingYardsInput.getText().trim()));
                        stats.setRushingTouchdowns(Double.parseDouble(rushingTouchdownsInput.getText().trim()));
                        break;
                    case "RB":
                        stats.setRushingAttempts(Double.parseDouble(rushingAttemptsInput.getText().trim()));
                        stats.setRushingYards(Double.parseDouble(rushingYardsInput.getText().trim()));
                        stats.setRushingTouchdowns(Double.parseDouble(rushingTouchdownsInput.getText().trim()));
                        stats.setReceptions(Integer.parseInt(receptionsInput.getText().trim()));
                        break;
                    case "WR":
                    case "TE":
                        stats.setReceptions(Integer.parseInt(receptionsInput.getText().trim()));
                        stats.setReceivingYards(Double.parseDouble(receivingYardsInput.getText().trim()));
                        stats.setReceivingTouchdowns(Double.parseDouble(receivingTouchdownsInput.getText().trim()));
                        break;
                    case "K":
                        stats.setFieldGoalsAttempted(Double.parseDouble(fGAInput.getText().trim()));
                        stats.setFieldGoalsMade(Integer.parseInt(fGMInput.getText().trim()));
                        stats.setExtraPointsMade(Integer.parseInt(extraPointsMadeInput.getText().trim()));
                        stats.setFieldGoalPercentage(Double.parseDouble(fGPercentageInput.getText().trim()));
                        break;
                    default:
                        resultLabel.setText("Invalid position. Please use QB, RB, WR, TE, or K.");
                        return;
                }

                player.setStats(stats);
                playerService.addPlayerToDatabase(player);
                resultLabel.setText(name + " has been added to the database.");

            } catch (NumberFormatException e) {
                resultLabel.setText("Please enter valid numbers for the statistics.");
            }
        } else {
            resultLabel.setText("Please fill in all required fields.");
        }
    }

    /**
     * Removes a player from the database.
     *
     * @param playerName the name of the player to be removed.
     * @return {@code true} if the player was removed successfully; {@code false} otherwise.
     */
    public boolean removePlayerFromDatabase(String playerName) {
        String query = "DELETE FROM players WHERE name = ?";

        try (Connection connection = PlayerService.DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, playerName);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a player based on the input field and updates the result label.
     */
    private void removePlayer() {
        String name = removePlayerInput.getText().trim();

        if (!name.isEmpty()) {
            try {
                boolean isRemoved = playerService.removePlayerFromDatabase(name);

                if (isRemoved) {
                    resultLabel.setText("Player " + name + " has been removed from the database.");
                } else {
                    resultLabel.setText("Player " + name + " not found in the database.");
                }
            } catch (Exception e) {
                resultLabel.setText("Error removing player: " + e.getMessage());
            }
        } else {
            resultLabel.setText("Please enter a player name to remove.");
        }
    }

    /**
     * Retrieves all players from the database and displays them in the player details label.
     */
    private void viewPlayers() {
        List<Player> players = playerService.getAllPlayersFromDatabase();
        if (players.isEmpty()) {
            playerDetails.setText("No players available.");
        } else {
            StringBuilder playerList = new StringBuilder("<html>Players List:<br>");
            for (Player player : players) {
                playerList.append(player.getName()).append(" - ")
                        .append(player.getPosition()).append(" - ")
                        .append(player.getTeam()).append("<br>");
            }
            playerList.append("</html>");
            playerDetails.setText(playerList.toString());
        }
    }
}