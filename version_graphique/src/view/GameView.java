package view;

import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.view.RootPane;
import boardifier.view.View;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.StageModel;

import java.util.List;

/**
 * Class that add all the different objects look to the game
 */
public class GameView extends View {
    public GameView(Model model, Stage stage, RootPane rootPane) {
        super(model, stage, rootPane);
    }

    private MenuItem menuStart;
    private MenuItem menuIntro;
    private MenuItem menuQuit;

    @Override
    protected void createMenuBar() {
         menuBar = new MenuBar();
         Menu menu1 = new Menu("Game");
         menuStart = new MenuItem("New game");
         menuIntro = new MenuItem("Intro");
         menuQuit = new MenuItem("Quit");
         menu1.getItems().add(menuStart);
         menu1.getItems().add(menuIntro);
         menu1.getItems().add(menuQuit);
         menuBar.getMenus().add(menu1);
     }

    public void showGameSetupDialog() {
        StageModel stageModel = (StageModel) model.getGameStage();
        // Create a new alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Setup");
        alert.setHeaderText("Configure your game settings");

        // GridPane for layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Text fields for player names
        Label player1Label = new Label("Player 1 Name:");
        TextField player1TextField = new TextField("Player 1");
        player1TextField.setPromptText("Enter Player 1 Name");

        Label player2Label = new Label("Player 2 Name:");
        TextField player2TextField = new TextField("Player 2");
        player2TextField.setPromptText("Enter Player 2 Name");

        // Dropdown for game variant
        Label variantLabel = new Label("Game Variant:");
        ComboBox<Integer> variantComboBox = new ComboBox<>();
        variantComboBox.getItems().addAll(1, 2);
        variantComboBox.setValue(1);  // Set default value

        // Text field for number of shells
        Label shellsLabel = new Label("Number of Shells:");
        TextField shellsTextField = new TextField("35");

        // Update default shells value based on variant
        variantComboBox.setOnAction(e -> {
            if (variantComboBox.getValue() == 1) {
                shellsTextField.setText("35");
            } else {
                shellsTextField.setText("50");
            }
        });

        // Ensure number of shells is within valid range
        shellsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int shells = Integer.parseInt(newValue);
                int minShells = variantComboBox.getValue() == 1 ? 35 : 50;
                if (shells < minShells || shells > 100) {
                    shellsTextField.setStyle("-fx-border-color: red;");
                } else {
                    shellsTextField.setStyle(null);
                }
            } catch (NumberFormatException ex) {
                shellsTextField.setStyle("-fx-border-color: red;");
            }
        });

        // Dropdown for game mode
        Label modeLabel = new Label("Game Mode:");
        ComboBox<String> modeComboBox = new ComboBox<>();
        modeComboBox.getItems().addAll("Player vs Player", "Player vs AI", "AI vs AI");
        modeComboBox.setValue("Player vs Player");  // Set default value

        // Update player names based on selected game mode
        modeComboBox.setOnAction(e -> {
            String selectedMode = modeComboBox.getValue();
            if ("Player vs Player".equals(selectedMode)) {
                player1TextField.setText("Player 1");
                player2TextField.setText("Player 2");
            } else if ("Player vs AI".equals(selectedMode)) {
                player1TextField.setText("Player");
                player2TextField.setText("Computer");
            } else if ("AI vs AI".equals(selectedMode)) {
                player1TextField.setText("Computer 1");
                player2TextField.setText("Computer 2");
            }
        });

        // Dropdown for AI difficulty
        Label difficultyLabel = new Label("AI Difficulty:");
        ComboBox<Integer> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll(1, 2, 3);
        difficultyComboBox.setValue(1);  // Set default value
        difficultyComboBox.setDisable(true);  // Initially disable

        // Enable/Disable AI difficulty based on selected game mode
        modeComboBox.setOnAction(e -> {
            String selectedMode = modeComboBox.getValue();
            if ("Player vs AI".equals(selectedMode) || "AI vs AI".equals(selectedMode)) {
                difficultyComboBox.setDisable(false);
            } else {
                difficultyComboBox.setDisable(true);
            }
        });

        // Add controls to the grid
        grid.add(player1Label, 0, 0);
        grid.add(player1TextField, 1, 0);
        grid.add(player2Label, 0, 1);
        grid.add(player2TextField, 1, 1);
        grid.add(variantLabel, 0, 2);
        grid.add(variantComboBox, 1, 2);
        grid.add(shellsLabel, 0, 3);
        grid.add(shellsTextField, 1, 3);
        grid.add(modeLabel, 0, 4);
        grid.add(modeComboBox, 1, 4);
        grid.add(difficultyLabel, 0, 5);
        grid.add(difficultyComboBox, 1, 5);

        // Set the grid as the expandable content of the alert
        alert.getDialogPane().setContent(grid);

        // Show the dialog and wait for the user's response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String player1Name = player1TextField.getText();
                String player2Name = player2TextField.getText();
                int variant = variantComboBox.getValue();
                stageModel.setGameVariant(variant);
                if (variant == 1) {
                    stageModel.setShipIndexLimit(5);
                } else if (variant == 2) {
                    stageModel.setShipIndexLimit(10);
                }
                int shells = Integer.parseInt(shellsTextField.getText());
                stageModel.setShellLimit(shells);
                String mode = modeComboBox.getValue();
                List<Player> players = model.getPlayers();
                players.clear();
                if (mode == "AI vs AI") {
                    model.addComputerPlayer(player1Name);
                    model.addComputerPlayer(player2Name);
                } else if (mode == "Player vs AI") {
                    model.addHumanPlayer(player1Name);
                    model.addComputerPlayer(player2Name);
                } else if (mode == "Player vs Player") {
                    model.addHumanPlayer(player1Name);
                    model.addHumanPlayer(player2Name);
                }
                int difficulty = difficultyComboBox.getValue();
                stageModel.setAiDifficulty(difficulty);
                for (Player player : players) {
                    System.out.println(player);
                }

                // Print the selected options (for demonstration)
                System.out.println("Player 1 Name: " + player1Name);
                System.out.println("Player 2 Name: " + player2Name);
                System.out.println("Game Variant: " + variant);
                System.out.println("Number of Shells: " + shells);
                System.out.println("Game Mode: " + mode);
                if (!difficultyComboBox.isDisabled()) {
                    System.out.println("AI Difficulty: " + difficulty);
                }
            }
        });
    }

    public MenuItem getMenuStart() {
        return menuStart;
    }

    public MenuItem getMenuIntro() {
        return menuIntro;
    }

    public MenuItem getMenuQuit() {
        return menuQuit;
    }
}
