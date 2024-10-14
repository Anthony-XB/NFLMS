package com.CEN30241.nflms.Controllers;

import com.CEN30241.nflms.Repositories.Player;
import com.CEN30241.nflms.Repositories.Stats;
import com.CEN30241.nflms.service.PlayerService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.List;


public class Menu extends Application {

    private PlayerService playerService = new PlayerService();
    private TextField playerInput;
    private TextField positionInput;
    private TextField teamInput;
    private TextField filePathInput;

    private TextField player1Input;

    private TextField player2Input;

    private Label playerDetails;
    private Label statusLabel;

    private Label resultLabel;

    private Label compareResultLabel;



        @Override
        public void start(Stage stage) {
            VBox layout = new VBox(20);


            filePathInput = new TextField("Enter file path for players.txt");
            Button loadFileButton = new Button("Load Players from File");
            statusLabel = new Label();


            loadFileButton.setOnAction(event -> {
                String filePath = filePathInput.getText().trim();
                if (!filePath.isEmpty()) {
                    playerService.loadPlayersFromFile(filePath);
                    statusLabel.setText("Players loaded from: " + filePath);
                } else {
                    statusLabel.setText("Please enter a valid file path.");
                }
            });

            playerInput = new TextField("Enter Player Name");
            positionInput = new TextField("Enter Player Position");
            teamInput = new TextField("Enter Player Team");
            player1Input = new TextField("Enter Player Name");
            player2Input = new TextField("Enter Player Name");

            playerDetails = new Label();
            resultLabel = new Label();
            compareResultLabel = new Label();


            Button viewPlayersButton = new Button("View Players");
            viewPlayersButton.setOnAction(event -> viewPlayers());

            Button addPlayerButton = new Button("Add Player");
            addPlayerButton.setOnAction(event -> addPlayer());

            Button removePlayerButton = new Button("Remove Player");
            removePlayerButton.setOnAction(event -> removePlayer());

            Button saveToFileButton = new Button("Save Changes");
            saveToFileButton.setOnAction(event -> {
                String filePath = filePathInput.getText().trim();
                playerService.savePlayersToFile(filePath);
                statusLabel.setText("Changes saved to: " + filePath);
            });

            Button compareStatsButton = new Button("Compare Stats");
            compareStatsButton.setOnAction(event -> comparePlayerStats());

            layout.getChildren().addAll(
                    filePathInput, loadFileButton, playerInput, positionInput, teamInput,
                    viewPlayersButton, playerDetails, addPlayerButton, resultLabel,player1Input,player2Input, compareStatsButton, compareResultLabel, removePlayerButton, saveToFileButton, statusLabel
            );

            Scene scene = new Scene(layout, 800, 800);
            stage.setScene(scene);
            stage.setTitle("Player Manager");
            stage.show();
        }


        private void viewPlayers() {
            List<Player> players = playerService.getPlayers();  // Get the list of players
            if (players.isEmpty()) {
                playerDetails.setText("No players available.");
                return;
            }

            StringBuilder playerList = new StringBuilder("Players List:\n");
            for (Player player : players) {
                playerList.append(player.getName()).append(" - ").append(player.getPosition())
                        .append(" - ").append(player.getTeam()).append("\n");
            }
            playerDetails.setText(playerList.toString());  // Display the list of players
        }

    private void addPlayer() {
        String name = playerInput.getText().trim();
        String position = positionInput.getText().trim();
        String team = teamInput.getText().trim();

        if (!name.isEmpty() && !position.isEmpty() && !team.isEmpty()) {
            Player player = new Player(name, position, team);
            playerService.addPlayer(player);
            resultLabel.setText(name + " has been added.");
        } else {
            resultLabel.setText("Please fill in all fields.");
        }
    }

    private void removePlayer() {
        String name = playerInput.getText().trim();

        if (!name.isEmpty()) {
            playerService.removePlayer(name);
            resultLabel.setText(name + " has been removed.");
        } else {
            resultLabel.setText("Please enter a player name.");
        }
    }

    private void savePlayersToFile() {
        String filePath = filePathInput.getText().trim();
        if (!filePath.isEmpty()) {
            playerService.savePlayersToFile(filePath);
            resultLabel.setText("Players have been saved to: " + filePath);
        } else {
            resultLabel.setText("Please enter a valid file path.");
        }
    }

    private void comparePlayerStats() {
        String player1Name = player1Input.getText().trim();
        String player2Name = player2Input.getText().trim();

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            compareResultLabel.setText("Please enter both player names.");
            return;
        }


        List<Stats> statsList = playerService.getPlayersStatsForComparison(player1Name, player2Name);

        if (statsList.size() == 2) {
            Stats s1 = statsList.get(0);
            Stats s2 = statsList.get(1);


            compareResultLabel.setText(
                    "Player 1: " + player1Name + " | Player 2: " + player2Name + "\n" +
                            "Passing Attempts: " + s1.getPassingAttempts() + " | " + s2.getPassingAttempts() + "\n" +
                            "Passing Completions: " + s1.getPassingCompletions() + " | " + s2.getPassingCompletions() + "\n" +
                            "Passing Yards: " + s1.getPassingYards() + " | " + s2.getPassingYards() + "\n" +
                            "Rushing Attempts: " + s1.getRushingAttempts() + " | " + s2.getRushingAttempts() + "\n" +
                            "Rushing Yards: " + s1.getRushingYards() + " | " + s2.getRushingYards() + "\n" +
                            "Field Goals Attempted: " + s1.getFieldGoalsAttempted() + " | " + s2.getFieldGoalsAttempted() + "\n" +
                            "Field Goals Made: " + s1.getFieldGoalsMade() + " | " + s2.getFieldGoalsMade()
            );
        } else {
            compareResultLabel.setText("Stats not found for one or both players.");
        }
    }



}
