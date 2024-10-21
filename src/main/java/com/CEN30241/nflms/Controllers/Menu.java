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

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class Menu extends Application {

    private PlayerService playerService = new PlayerService();
    private TextField playerInput;
    private TextField positionInput;
    private TextField teamInput;
    private TextField filePathInput;

    private TextField removePlayerInput;

    private TextField player1Input;
    private TextField player2Input;

    private Label playerDetails;
    private Label statusLabel;
    private Label resultLabel;

    private Label compareResultLabel;

    private TextField passingYardsInput;
    private TextField passingTouchdownsInput;
    private TextField interceptionsInput;
    private TextField rushingAttemptsInput;
    private TextField rushingYardsInput;
    private TextField rushingTouchdownsInput;
    private TextField receptionsInput;
    private TextField receivingYardsInput;
    private TextField receivingTouchdownsInput;
    private TextField fGAInput;
    private TextField fGMInput;
    private TextField extraPointsMadeInput;
    private TextField fGPercentageInput;


    private Label passingYardsLabel;
    private Label passingTouchdownsLabel;
    private Label interceptionsLabel;
    private Label rushingAttemptsLabel;
    private Label rushingYardsLabel;
    private Label rushingTouchdownsLabel;
    private Label receptionsLabel;
    private Label receivingYardsLabel;
    private Label receivingTouchdownsLabel;
    private Label fGALabel;
    private Label fGMLabel;
    private Label extraPointsMadeLabel;
    private Label fGPercentageLabel;

    @Override
    public void start(Stage stage) {
        showMainMenu(stage);
    }

    private void showMainMenu(Stage stage) {
        VBox mainMenuLayout = new VBox(20);

        // File path input and browse button
        filePathInput = new TextField("Enter file path for players.txt");

        Button browseButton = new Button("Browse...");
        browseButton.setOnAction(event -> chooseFile());

        Button loadFileButton = new Button("Load Players from File");
        statusLabel = new Label();
        playerDetails = new Label();
        resultLabel = new Label();

        loadFileButton.setOnAction(event -> loadPlayers());


        playerInput = new TextField("Enter Player Name");
        positionInput = new TextField("Enter Player Position");
        teamInput = new TextField("Enter Player Team");


        removePlayerInput = new TextField("Enter Player Name to Remove");
        Button removePlayerButton = new Button("Remove Player");

        removePlayerButton.setOnAction(event -> removePlayer());

        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setOnAction(event -> showAddPlayerScene(stage));

        Button viewPlayersButton = new Button("View Players");
        viewPlayersButton.setOnAction(event -> showViewPlayersScene(stage));

        Button compareStatsButton = new Button("Compare Stats");
        compareStatsButton.setOnAction(event -> showCompareStatsScene(stage));


        mainMenuLayout.getChildren().addAll(
                filePathInput, browseButton, loadFileButton,
                addPlayerButton,removePlayerInput, removePlayerButton, statusLabel, resultLabel,
                playerDetails,viewPlayersButton, compareStatsButton
        );

        Scene scene = new Scene(mainMenuLayout, 800, 800);
        stage.setScene(scene);
        stage.setTitle("Player Manager");
        stage.show();
    }


    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Player Data File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathInput.setText(selectedFile.getAbsolutePath());
        } else {
            statusLabel.setText("File selection canceled.");
        }
    }

    private void loadPlayers() {
        String filePath = filePathInput.getText().trim();
        if (!filePath.isEmpty()) {
            playerService.loadPlayersFromFile(filePath);
            statusLabel.setText("Players loaded from: " + filePath);
        } else {
            statusLabel.setText("Please enter a valid file path.");
        }
    }

    private void showAddPlayerScene(Stage stage) {
        VBox addPlayerLayout = new VBox(20);
        playerInput = new TextField("Enter Player Name");
        positionInput = new TextField("Enter Player Position");
        teamInput = new TextField("Enter Player Team");

        // Initialize labels for statistics
        passingYardsLabel = new Label("Passing Yards");
        passingYardsInput = new TextField();

        passingTouchdownsLabel = new Label("Passing Touchdowns");
        passingTouchdownsInput = new TextField();

        interceptionsLabel = new Label("Interceptions");
        interceptionsInput = new TextField();

        rushingAttemptsLabel = new Label("Rushing Attempts");
        rushingAttemptsInput = new TextField();

        rushingYardsLabel = new Label("Rushing Yards");
        rushingYardsInput = new TextField();

        rushingTouchdownsLabel = new Label("Rushing Touchdowns");
        rushingTouchdownsInput = new TextField();

        receptionsLabel = new Label("Receptions");
        receptionsInput = new TextField();

        receivingYardsLabel = new Label("Receiving Yards");
        receivingYardsInput = new TextField();

        receivingTouchdownsLabel = new Label("Receiving Touchdowns");
        receivingTouchdownsInput = new TextField();

        fGALabel = new Label("Field Goals Attempted");
        fGAInput = new TextField();

        fGMLabel = new Label("Field Goals Made");
        fGMInput = new TextField();

        extraPointsMadeLabel = new Label("Extra Points Made");
        extraPointsMadeInput = new TextField();

        fGPercentageLabel = new Label("Field Goal Percentage");
        fGPercentageInput = new TextField();

        resultLabel = new Label();

        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setOnAction(event -> addPlayer());

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> showMainMenu(stage));

        addPlayerLayout.getChildren().addAll(
                playerInput, positionInput, teamInput,
                passingYardsLabel, passingYardsInput,
                passingTouchdownsLabel, passingTouchdownsInput,
                interceptionsLabel, interceptionsInput,
                rushingAttemptsLabel, rushingAttemptsInput,
                rushingYardsLabel, rushingYardsInput,
                rushingTouchdownsLabel, rushingTouchdownsInput,
                receptionsLabel, receptionsInput,
                receivingYardsLabel, receivingYardsInput,
                receivingTouchdownsLabel, receivingTouchdownsInput,
                fGALabel, fGAInput,
                fGMLabel, fGMInput,
                extraPointsMadeLabel, extraPointsMadeInput,
                fGPercentageLabel, fGPercentageInput,
                addPlayerButton, resultLabel, backButton
        );

        Scene addPlayerScene = new Scene(addPlayerLayout, 800, 800);
        stage.setScene(addPlayerScene);
    }

    private void addPlayer() {
        String name = playerInput.getText().trim();
        String position = positionInput.getText().trim();
        String team = teamInput.getText().trim();


        Stats stats = new Stats();

        if (!name.isEmpty() && !position.isEmpty() && !team.isEmpty()) {
            Player player = new Player(name, position, team);


            switch (position) {
                case "QB":
                    double passingYards = Double.parseDouble(passingYardsInput.getText().trim());
                    double passingTouchdowns = Double.parseDouble(passingTouchdownsInput.getText().trim());
                    double interceptions = Double.parseDouble(interceptionsInput.getText().trim());
                    double rushingAttempts = Double.parseDouble(rushingAttemptsInput.getText().trim());
                    double rushingYards = Double.parseDouble(rushingYardsInput.getText().trim());
                    double rushingTouchdowns = Double.parseDouble(rushingTouchdownsInput.getText().trim());

                    stats.setPassingYards(passingYards);
                    stats.setPassingTouchdowns(passingTouchdowns);
                    stats.setInterceptions(interceptions);
                    stats.setRushingAttempts(rushingAttempts);
                    stats.setRushingYards(rushingYards);
                    stats.setRushingTouchdowns(rushingTouchdowns);
                    break;


                case "RB":
                    rushingAttempts = Double.parseDouble(rushingAttemptsInput.getText().trim());
                    rushingYards = Double.parseDouble(rushingYardsInput.getText().trim());
                    rushingTouchdowns = Double.parseDouble(rushingTouchdownsInput.getText().trim());
                    double receptions = Double.parseDouble(receptionsInput.getText().trim());
                    double receivingYards = Double.parseDouble(receivingYardsInput.getText().trim());
                    double receivingTouchdowns = Double.parseDouble(receivingTouchdownsInput.getText().trim());

                    stats.setRushingAttempts(rushingAttempts);
                    stats.setRushingYards(rushingYards);
                    stats.setRushingTouchdowns(rushingTouchdowns);
                    stats.setReceptions(receptions);
                    stats.setReceivingYards(receivingYards);
                    stats.setReceivingTouchdowns(receivingTouchdowns);
                    break;
                case "WR", "TE":
                    receptions = Double.parseDouble(receptionsInput.getText().trim());
                    receivingYards = Double.parseDouble(receivingYardsInput.getText().trim());
                    receivingTouchdowns = Double.parseDouble(receivingTouchdownsInput.getText().trim());

                    stats.setReceptions(receptions);
                    stats.setReceivingYards(receivingYards);
                    stats.setReceivingTouchdowns(receivingTouchdowns);
                    break;

                case "K":
                    double fieldGoalsAttempted = Double.parseDouble(fGAInput.getText().trim());
                    double fieldGoalsMade = Double.parseDouble(fGMInput.getText().trim());
                    double extraPointsMade = Double.parseDouble(extraPointsMadeInput.getText().trim());
                    double fieldGoalPercentage = Double.parseDouble(fGPercentageInput.getText().trim());

                    stats.setFieldGoalsAttempted(fieldGoalsAttempted);
                    stats.setFieldGoalsMade(fieldGoalsMade);
                    stats.setExtraPointsMade(extraPointsMade);
                    stats.setFieldGoalPercentage(fieldGoalPercentage);
                    break;

            }

            player.setStats(stats);

            String filePath = filePathInput.getText().trim();
            playerService.addPlayer(filePath, player);
            resultLabel.setText(name + " has been added.");
        } else {
            resultLabel.setText("Please fill in all fields.");
        }

    }

    private void removePlayer() {
        String name = removePlayerInput.getText().trim();
        String filePath = filePathInput.getText().trim();

        if (!filePath.isEmpty() && !name.isEmpty()) {
            playerService.removePlayer(filePath, name);
            statusLabel.setText("Removed player: " + name + " from " + filePath);
        } else {
            statusLabel.setText("Please enter a valid file path and player name.");
        }


    }

    private void showViewPlayersScene(Stage stage) {
        VBox viewPlayersLayout = new VBox(20);
        playerDetails = new Label();

        Button viewPlayersButton = new Button("View Players");
        viewPlayersButton.setOnAction(event -> viewPlayers());

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> showMainMenu(stage));

        viewPlayersLayout.getChildren().addAll(viewPlayersButton, playerDetails, backButton);

        stage.setScene(new Scene(viewPlayersLayout, 800, 800));
    }

    private void viewPlayers() {
        List<Player> players = playerService.getPlayers();
        if (players.isEmpty()) {
            playerDetails.setText("No players available.");
            return;
        }

        StringBuilder playerList = new StringBuilder("Players List:\n");
        for (Player player : players) {
            playerList.append(player.getName()).append(" - ").append(player.getPosition())
                    .append(" - ").append(player.getTeam()).append("\n");
        }
        playerDetails.setText(playerList.toString());
    }

    private void showCompareStatsScene(Stage stage) {
        VBox compareStatsLayout = new VBox(20);

        player1Input = new TextField("Enter Player 1 Name");
        player2Input = new TextField("Enter Player 2 Name");
        compareResultLabel = new Label();

        Button compareButton = new Button("Compare Stats");
        compareButton.setOnAction(event -> comparePlayerStats());

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> showMainMenu(stage));

        compareStatsLayout.getChildren().addAll(
                player1Input, player2Input, compareButton, compareResultLabel, backButton
        );

        stage.setScene(new Scene(compareStatsLayout, 800, 800));
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
                            "Field Goals Made: " + s1.getFieldGoalsMade() + " | " + s2.getFieldGoalsMade() + "\n" +
                            "Receptions: " + s1.getReceptions() + " | " + s2.getReceptions() + "\n" +
                            "Receiving Yards: " + s1.getReceivingYards() + " | " + s2.getReceivingYards() + "\n" +
                            "Receiving Touchdowns: " + s1.getReceivingTouchdowns() + " | " + s2.getReceivingTouchdowns()
            );
        } else {
            compareResultLabel.setText("Stats not found for one or both players.");
        }
    }
}
