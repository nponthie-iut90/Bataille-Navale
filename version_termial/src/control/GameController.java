package control;

import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.view.View;
import model.ShipPart;
import model.StageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class GameController extends Controller {
    boolean running = true;

    public void setRunningTrue() {
        running = true;
    }

    public void setBotDifficulty(int difficulty) {
        this.botDifficulty = difficulty;
    }

    public void askPlayerShellLimits() {
        String answer;
        printSystemInstruction("Do you want to play with more shells ?");
        try {
            boolean ok = false;
            while (!ok) {
                switch (gameVariant) {
                    case 1:
                        printSystemInstruction("Enter a number > 35 & < 100 or enter nothing if 35 shells is enough for you");
                        break;
                    case 2:
                        printSystemInstruction("Enter a number > 50 & < 100 or enter nothing if 50 shells is enough for you");
                        break;
                }
                System.out.print("Players > ");
                answer = consoleIn.readLine();
                if (answer.length() == 2) {
                    int verification;
                    verification = Integer.parseInt(String.valueOf(answer.charAt(0)) + String.valueOf(answer.charAt(1)));
                    switch (gameVariant) {
                        case 1:
                            if (verification < 35 || verification >= 100) {
                                printSystemInstruction("The number of shells must be between 35 and 100");
                            } else {
                                shellLimit = verification;
                                ok = true;
                            }
                            break;
                        case 2:
                            if (verification < 50 || verification >= 100) {
                                printSystemInstruction("The number of shells must be between 50 and 100");
                            } else {
                                shellLimit = verification;
                                ok = true;
                            }
                            break;
                    }
                } else if (answer.isEmpty()) {
                    ok = true;
                }
            }

        } catch (IOException e) {}
    }

    BufferedReader consoleIn;
    int gameVariant = 0;
    int currentPlayer = 0;
    int botShipIndex = 0;
    int player1AttackMarkIndex = 0;
    int player2AttackMarkIndex = 0;
    int shellLimit = 0;
    int destroyedShipPartsByPlayer1 = 0;
    int destroyedShipPartsByPlayer2 = 0;
    int destroyedShipsByPlayer1 = 0;
    int destroyedShipsByPlayer2 = 0;
    String temporaryMessageForPlayer1 = null;
    String temporaryMessageForPlayer2 = null;
    int botDifficulty;

    public void defineShellLimit() {
        switch(gameVariant) {
            case 1:
                shellLimit = 35;
                break;
            case 2:
                shellLimit = 50;
        }
    }


    /**
     * Simply stop the game at the
     */
    public void stopTheGame() {
        running = false;
    }

    /**
     * Utility method to set the buffered reader to be used by the game controller
     *
     * @param consoleIn the buffered reader to be used
     */
    public void setReader(BufferedReader consoleIn) {
        this.consoleIn = consoleIn;
    }

    /**
     * Display if the player 1 has lost one of his ship part or ship during the previous turn
     */
    public void displayTemporaryMessageForPlayer1IfExists() {
        if (temporaryMessageForPlayer1 != null) {
            printSystemInstruction(temporaryMessageForPlayer1);
        }
        temporaryMessageForPlayer1 = null;
    }

    /**
     * Display if the player 2 has lost one of his ship part or ship during the previous turn
     */
    public void displayTemporaryMessageForPlayer2IfExists() {
        if (temporaryMessageForPlayer2 != null) {
            printSystemInstruction(temporaryMessageForPlayer2);
        }
        temporaryMessageForPlayer2 = null;
    }

    public GameController(Model model, View view) {
        super(model, view);
    }

    /**
     * Method which is the game logic
     */
    public void stageLoop() {
        defineGameVariant();
        defineShellLimit();
        askPlayerShellLimits();
        defineFirstPlayerToPlay();
        if (currentPlayer == 1) {
            setPlayer1Ships();
            setPlayer2Ships();
        } else {
            setPlayer2Ships();
            setPlayer1Ships();
        }
        while (!isEndOfTheGame() && running) {
            playTurn();
        }
        displayResults();
    }

    /**
     * Utility method to display information to players
     *
     * @param instruction the message to be written
     */
    public void printSystemInstruction(String instruction) {
        System.out.println("System > " + instruction);
    }

    /**
     * Utility method to put the name of the player in the terminal before asking him to play
     */
    public void printPlayerName() {
        System.out.print(model.getCurrentPlayer().getName() + " > ");
    }

    /**
     * Ask for the players the game variant they want to play
     */
    public void defineGameVariant() {
        printSystemInstruction("You can play 2 variants");
        printSystemInstruction("Variant 1, each player has: 1 aircraft-carrier(5), 1 cruiser(4), 2 destroyer(3), 1 torpedo boat(2); 35 shells for each player");
        printSystemInstruction("Variant 2, each player has: 1 battleship(4), 2 cruiser(3), 3 torpedo boats(2), 4 submarine(1); 50 shells for each player");
        printSystemInstruction("Which variant to play ? (1 or 2)");
        boolean ok = false;
        while (!ok) {
            System.out.print("Players > ");
            try {
                String line = consoleIn.readLine();
                if (line.length() == 1) {
                    if (line.charAt(0) == '1') {
                        ok = true;
                        gameVariant = 1;
                    } else if (line.charAt(0) == '2') {
                        ok = true;
                        gameVariant = 2;
                    }
                } else {
                    printSystemInstruction("input error !");
                }
            } catch (IOException e) {
            }
        }
        printSystemInstruction("Game variant " + gameVariant + " selected !");
    }

    /**
     * Random selection to select the player to start
     */
    public void defineFirstPlayerToPlay() {
        printSystemInstruction("Which player should start ? (1 or 2)");
        printSystemInstruction("Press 'enter' to randomly select the player to start !");
        String line = "";
        int playerToStart = 1;
        boolean ok = false;
        while (!ok) {
            System.out.print("Players > ");
            try {
                line = consoleIn.readLine();
            } catch (IOException e) {
            }
            if (line.isEmpty()) {
                Random random = new Random();
                playerToStart = random.nextInt(2) + 1;
                if (playerToStart == 2) {
                    model.setNextPlayer();
                }
                currentPlayer = playerToStart;
                ok = true;
            } else {
                if (line.charAt(0) == '2') {
                    model.setNextPlayer();
                    playerToStart = 2;
                    ok = true;
                } else if (line.charAt(0) == '1') {
                    ok = true;
                } else {
                    printSystemInstruction("Input error !");
                }
                currentPlayer = playerToStart;
            }
        }
        printSystemInstruction("The player " + playerToStart + " start !");
    }

    /**
     * Switch players after an end of turn
     */
    public void switchPlayer() {
        printSystemInstruction("Press 'enter' to pass control to your opponent");
        try {
            consoleIn.readLine();
        } catch (IOException e) {
        }
        model.setNextPlayer();
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    /**
     * Display instructions for the player to set his ship;
     *
     * @param shipNumber the ship to be placed
     */
    public void getInstructionsForPlayer(int shipNumber) {
        StageModel stageModel = (StageModel) model.getGameStage();
        printSystemInstruction("First enter the inclination of your ship with H(horizontal) or V(vertical), then enter a coordinate.");
        printSystemInstruction("The ship will be placed left to right (H) or top to bottom (V)");
        printSystemInstruction("Example : HC7, VD3, HA10, ...");
        switch (gameVariant) {
            case 1:
                switch (currentPlayer) {
                    case 1:
                        printSystemInstruction("Enter coordinates for your " + stageModel.getPlayer1ShipsV1().get(shipNumber).getName() + " (size: " + stageModel.getPlayer1ShipsV1().get(shipNumber).getSize() + ")");
                        break;
                    case 2:
                        printSystemInstruction("Enter coordinates for your  " + stageModel.getPlayer2ShipsV1().get(shipNumber).getName() + " (size: " + stageModel.getPlayer2ShipsV1().get(shipNumber).getSize() + ")");
                        break;
                }
                break;
            case 2:
                switch (currentPlayer) {
                    case 1:
                        printSystemInstruction("Enter coordinates for your  " + stageModel.getPlayer1ShipsV2().get(shipNumber).getName() + " (size: " + stageModel.getPlayer1ShipsV2().get(shipNumber).getSize() + ")");
                        break;
                    case 2:
                        printSystemInstruction("Enter coordinates for your  " + stageModel.getPlayer2ShipsV2().get(shipNumber).getName() + " (size: " + stageModel.getPlayer2ShipsV2().get(shipNumber).getSize() + ")");
                        break;
                }
                break;
        }
    }

    /**
     * Method to ask player 1 to place his ships
     */
    public void setPlayer1Ships() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (model.getCurrentPlayer().getType() == Player.COMPUTER) {
            int length = 0;
            switch (gameVariant) {
                case 1:
                    length = 5;
                    break;
                case 2:
                    length = 10;
                    break;
            }

            System.out.println(model.getCurrentPlayer().getName() + " playing");
            for (int i = 0; i < length; i++) {
                ChatGPT chatGPT = new ChatGPT(model, this);
                ActionPlayer play = new ActionPlayer(model, this, chatGPT, null);
                play.start();
                botShipIndex++;
            }
            //switchPlayer();
            model.setNextPlayer();
            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
            botShipIndex = 0;
        } else {
            stageModel.showPlayer1Board();

            update();
            // index for the number of ship to place
            int totalShips = 0;
            // index for ship in table
            int ShipIndex = 0;
            switch (gameVariant) {
                case 1:
                    totalShips = 5;
                    break;
                case 2:
                    totalShips = 10;
                    break;
            }
            while (ShipIndex < totalShips) {
                getInstructionsForPlayer(ShipIndex);
                printPlayerName();
                boolean ok;
                try {
                    String line = consoleIn.readLine();
                    ok = analyzeAndPlace(line, ShipIndex);
                    if (ok) {
                        ShipIndex++;
                    }
                } catch (IOException e) {
                }
            }
            stageModel.hidePlayer1Board();
            update();
            printSystemInstruction("Player 1 has finished placing his ships!");
            switchPlayer();
        }
    }

    /**
     * Method to ask player 2 to place his ships
     */
    public void setPlayer2Ships() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (model.getCurrentPlayer().getType() == Player.COMPUTER) {
            int length = 0;
            switch (gameVariant) {
                case 1:
                    length = 5;
                    break;
                case 2:
                    length = 10;
                    break;
            }

            System.out.println(model.getCurrentPlayer().getName() + " is playing");
            for (int i = 0; i < length; i++) {
                ChatGPT chatGPT = new ChatGPT(model, this);
                ActionPlayer play = new ActionPlayer(model, this, chatGPT, null);
                play.start();
                botShipIndex++;
            }
            //switchPlayer();
            model.setNextPlayer();
            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
            botShipIndex = 0;
        } else {
            stageModel.showSecondBoard();

            update();
            // index for the number of ship to place
            int totalShips = 0;
            // index for ship in table
            int ShipIndex = 0;
            switch (gameVariant) {
                case 1:
                    totalShips = 5;
                    break;
                case 2:
                    totalShips = 10;
                    break;
            }
            while (ShipIndex < totalShips) {
                getInstructionsForPlayer(ShipIndex);
                printPlayerName();
                boolean ok;
                try {
                    String line = consoleIn.readLine();
                    ok = analyzeAndPlace(line, ShipIndex);
                    if (ok) {
                        ShipIndex++;
                    }
                } catch (IOException e) {
                }
            }
            stageModel.hidePlayer2Board();
            update();
            printSystemInstruction("Player 1 has finished placing his ships!");
            switchPlayer();
        }
    }

    /**
     * Method to analyse the line written by the player in the terminal
     *
     * @param line       the line to be analyzed
     * @param shipNumber the ship that has to be placed
     * @return true if the coordinates are correct and if the ship could be placed
     */
    public boolean analyzeAndPlace(String line, int shipNumber) {
        int colindex;
        int rowindex;
        boolean horizontal;
        if (line == null) {
            update();
            printSystemInstruction("Coordinates can not be empty !");
            return false;
        }
        if (line.length() != 3) {
            if (line.length() != 4) {
                update();
                printSystemInstruction("Error while entering coordinates !");
                return false;
            }
        } else if (line.charAt(0) != 'H') {
            if (line.charAt(0) != 'V') {
                update();
                printSystemInstruction("Error ");
                return false;
            }
        }
        if (line.length() == 3) {
            horizontal = line.charAt(0) == 'H';
            colindex = (line.charAt(1)) - 'A';
            rowindex = (line.charAt(2)) - '1';
        } else {
            horizontal = line.charAt(0) == 'H';
            colindex = line.charAt(1) - 'A';
            try {
                rowindex = Integer.parseInt(String.valueOf(line.charAt(2)) + String.valueOf(line.charAt(3)));
                rowindex--;
            } catch (NumberFormatException e) {
                update();
                printSystemInstruction("Input error !");
                return false;
            }
        }
        if (colindex < 0 || colindex > 9) {
            update();
            printSystemInstruction("The column index must be between A and J!");
            return false;
        }
        if (rowindex < 0 || rowindex > 9) {
            update();
            printSystemInstruction("The row index must be between 0 and 10!");
            return false;
        }
        return isShipWillBeInBoardAndLegal(horizontal, colindex, rowindex, shipNumber);
    }

    /**
     * Preview the position given by the player into a grid
     *
     * @param colindex the column index where the ship have to be placed
     * @param rowindex the row index where the ship have to be placed
     * @return a two-dimensional array representing the grid with the coordinate given by the player inside
     */
    public int[][] calcOriginalCaseNeededByTheShip(int colindex, int rowindex) {
        int[][] caseNeeded = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                caseNeeded[i][j] = 0;
            }
        }
        caseNeeded[rowindex][colindex] = -1;
        return caseNeeded;
    }

    /**
     * Verify if the ship can be placed into the grid without being partially outside the grid
     *
     * @param horizontal if the ship have to be placed horizontally or vertically
     * @param colindex   the column index where the ship have to be placed
     * @param rowindex   the row index where the ship have to be placed
     * @param shipNumber the ship to being placed
     * @return true if the ship won't be outside the grid
     */
    public boolean isShipWillBeInBoardAndLegal(boolean horizontal, int colindex, int rowindex, int shipNumber) {
        StageModel stageModel = (StageModel) model.getGameStage();
        int[][] startShipIndex = calcOriginalCaseNeededByTheShip(colindex, rowindex);
        switch (gameVariant) {
            case 1:
                switch (currentPlayer) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship will pass the gate on the right!");
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship is going to pass the gate at the bottom!");
                                                return false;
                                            }
                                            startShipIndex[i + 1][j] = -1;
                                            i++;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship will pass the gate on the right!");
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship is going to pass the gate at the bottom!");
                                                return false;
                                            }
                                            startShipIndex[i + 1][j] = -1;
                                            i++;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (currentPlayer) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship will pass the gate on the right!");
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship is going to pass the gate at the bottom!");
                                                return false;
                                            }
                                            startShipIndex[i + 1][j] = -1;
                                            i++;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship will pass the gate on the right!");
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
                                                update();
                                                printSystemInstruction("Invalid coordinate! The ship is going to pass the gate at the bottom!");
                                                return false;
                                            }
                                            startShipIndex[i + 1][j] = -1;
                                            i++;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
        }
        return isInValideCells(startShipIndex, shipNumber, horizontal, colindex, rowindex);
    }

    /**
     * Verify if the ship won't be on another ship or next to
     *
     * @param shipFutureCoordinates the planned coordinates of the ship
     * @param shipNumber            the ship that has to be placed
     * @param horizontal            if the ship has to be placed horizontally or vertically
     * @param colindex              the column index of the ship to be placed
     * @param rowindex              the row index of the ship to be placed
     * @return true if the ship won't be on another ship or next to
     */
    public boolean isInValideCells(int[][] shipFutureCoordinates, int shipNumber, boolean horizontal, int colindex, int rowindex) {
        StageModel stageModel = (StageModel) model.getGameStage();
        int[][] availableCoordinates = stageModel.calcValideCells(currentPlayer);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // side checks
                if (j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i][j - 1] == 1) {
                        availableCoordinates[i][j - 1] = 0;
                    }
                }
                if (j + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i][j + 1] == 1) {
                        availableCoordinates[i][j + 1] = 0;
                    }
                }
                if (i - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j] == 1) {
                        availableCoordinates[i - 1][j] = 0;
                    }
                }
                if (i + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j] == 1) {
                        availableCoordinates[i + 1][j] = 0;
                    }
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j - 1] == 1) {
                        availableCoordinates[i - 1][j - 1] = 0;
                    }
                }
                // corners check
                if (i - 1 >= 0 && j + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j + 1] == 1) {
                        availableCoordinates[i - 1][j + 1] = 0;
                    }
                }
                if (i + 1 <= 8 && j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j - 1] == 1) {
                        availableCoordinates[i + 1][j - 1] = 0;
                    }
                }
                if (i + 1 <= 9 && j + 1 <= 8) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j + 1] == 1) {
                        availableCoordinates[i + 1][j + 1] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (availableCoordinates[i][j] == 0) {
                    availableCoordinates[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (availableCoordinates[i][j] == shipFutureCoordinates[i][j]) {
                    update();
                    printSystemInstruction("Invalid coordinate! You cannot place a ship on top of another ship or stick it to another ship!");
                    return false;
                }
            }
        }

        addShipAfterCheck(shipNumber, horizontal, colindex, rowindex);
        return true;
    }

    /**
     * Add a ship to a player board after checking the coordinates
     *
     * @param shipNumber the ship to be place
     * @param horizontal if the ship has to be placed horizontally or vertically
     * @param colindex   the column index of the ship to be placed
     * @param rowindex   the row index of the ship to be placed
     */
    public void addShipAfterCheck(int shipNumber, boolean horizontal, int colindex, int rowindex) {
        StageModel stageModel = (StageModel) model.getGameStage();
        switch (gameVariant) {
            case 1:
                switch (currentPlayer) {
                    case 1:
                        stageModel.getPlayer1Board().addShipToBoard(stageModel.getPlayer1ShipsV1().get(shipNumber), rowindex, colindex, horizontal);
                        update();
                        printSystemInstruction("The " + stageModel.getPlayer1ShipsV1().get(shipNumber).getName() + " was successfully placed !");
                        break;
                    case 2:
                        stageModel.getPlayer2Board().addShipToBoard(stageModel.getPlayer2ShipsV1().get(shipNumber), rowindex, colindex, horizontal);
                        update();
                        printSystemInstruction("The " + stageModel.getPlayer2ShipsV1().get(shipNumber).getName() + " was successfully placed !");
                        break;
                }
                break;
            case 2:
                switch (currentPlayer) {
                    case 1:
                        stageModel.getPlayer1Board().addShipToBoard(stageModel.getPlayer1ShipsV2().get(shipNumber), rowindex, colindex, horizontal);
                        update();
                        printSystemInstruction("The " + stageModel.getPlayer1ShipsV2().get(shipNumber).getName() + " was successfully placed !");
                        break;
                    case 2:
                        stageModel.getPlayer2Board().addShipToBoard(stageModel.getPlayer2ShipsV2().get(shipNumber), rowindex, colindex, horizontal);
                        update();
                        printSystemInstruction("The " + stageModel.getPlayer2ShipsV2().get(shipNumber).getName() + " was successfully placed !");
                        break;
                }
                break;
        }
    }

    /**
     * Verify if the game is over
     *
     * @return true if one of the player has no more ship or if player have fired all of their shells
     */
    public boolean isEndOfTheGame() {
        StageModel stageModel = (StageModel) model.getGameStage();
        return stageModel.isPlayer1HasNoMoreShips() || stageModel.isPlayer2HasNoMoreShips() || (player1AttackMarkIndex >= shellLimit && player2AttackMarkIndex >= shellLimit);
    }

    /**
     * Ask for player to attack until the game is over
     */
    public void playTurn() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (model.getCurrentPlayer().getType() == Player.COMPUTER) {
            switch (botDifficulty) {
                case 1:
                    ChatGPTToPlay chatGPTToPlay = new ChatGPTToPlay(model, this);
                    ActionPlayer actionPlayer = new ActionPlayer(model, this, chatGPTToPlay, null);
                    actionPlayer.start();

                    model.setNextPlayer();
                    if (currentPlayer == 1) {
                        currentPlayer = 2;
                    } else {
                        currentPlayer = 1;
                    }
                    break;
                case 2:
                    CaiToPlay caiToPlay = new CaiToPlay(model, this);
                    ActionPlayer actionPlayer2 = new ActionPlayer(model, this, caiToPlay, null);
                    actionPlayer2.start();

                    model.setNextPlayer();
                    if (currentPlayer == 1) {
                        currentPlayer = 2;
                    } else {
                        currentPlayer = 1;
                    }
                    break;
            }
        } else {
            switch (currentPlayer) {
                case 1:
                    stageModel.showPlayer1Board();
                    stageModel.showPlayer1AttackBoard();
                    update();
                    displayTemporaryMessageForPlayer1IfExists();
                    break;
                case 2:
                    stageModel.showSecondBoard();
                    stageModel.showSecondAttackBoard();
                    update();
                    displayTemporaryMessageForPlayer2IfExists();
                    break;
            }
            //displayTemporaryMessageIfExists();
            printInstructionsForAttack();
            printPlayerName();

            boolean ok = false;
            while (!ok) {
                try {
                    String line = consoleIn.readLine();
                    if (line.equals("stop")) {
                        running = false;
                        return;
                    }
                    ok = analyzeAndAttack(line);
                    if (!ok) {
                        printPlayerName();
                    }
                } catch (IOException e) {
                }
            }
            switchPlayer();
        }
    }

    /**
     * Display instructions to the player on how to attack
     */
    public void printInstructionsForAttack() {
        printSystemInstruction("Enter the coordinates of the square to attack !");
        printSystemInstruction("First enter the column then the row of the box to attack !");
        printSystemInstruction("Example: A1, D7, B4, ...");
    }

    /**
     * Analyze the coordinate given by the player and shot on these coordinates if they are correct or the player didn't already fire on these coordinates
     *
     * @param line the coordinate given by the player
     * @return true if the attack could have take place on the given coordinates
     */
    public boolean analyzeAndAttack(String line) {
        StageModel stageModel = (StageModel) model.getGameStage();
        int rowindex;
        int colindex;

        if (line == null) {
            update();
            printSystemInstruction("The coordinate cannot be empty!");
            return false;
        }

        if (line.length() != 2) {
            if (line.length() != 3) {
                update();
                printSystemInstruction("Error entering coordinates !");
                return false;
            }
        }

        if (line.length() == 2) {
            colindex = line.charAt(0) - 'A';
            rowindex = line.charAt(1) - '1';
        } else {
            colindex = line.charAt(0) - 'A';
            try {
                rowindex = Integer.parseInt(Character.toString(line.charAt(1)) + Character.toString(line.charAt(2)));
                rowindex--;
            } catch (NumberFormatException e) {
                update();
                printSystemInstruction("The line coordinate must be a number!");
                return false;
            }

        }

        if (colindex < 0 || colindex > 9) {
            update();
            printSystemInstruction("The column index must be between A and J !");
            return false;
        }
        if (rowindex < 0 || rowindex > 9) {
            update();
            printSystemInstruction("The row index must be between 1 and 10 !");
            return false;
        }

        if ((stageModel.isPlayerHasAlreadyShotOnThisCase(rowindex, colindex, currentPlayer))) {
            printSystemInstruction("You have already drawn on this square");
            return false;
        }
        switch (currentPlayer) {
            case 1:
                if (stageModel.getPlayer2Board().isEmptyAt(rowindex, colindex)) {
                    stageModel.getPlayer1AttackMarks(player1AttackMarkIndex).setColor(0);
                    stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(player1AttackMarkIndex), rowindex, colindex);
                    stageModel.getPlayer2Board().addMarkToBoard(stageModel.getPlayer1AttackMarks(player1AttackMarkIndex), rowindex, colindex);

                    update();
                    printSystemInstruction("Splash !");
                    stageModel.hidePlayer1Board();
                    stageModel.hidePlayer1AttackBoard();
                } else {
                    stageModel.getPlayer1AttackMarks(player1AttackMarkIndex).setColor(1);
                    stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(player1AttackMarkIndex), rowindex, colindex);
                    ShipPart p = (ShipPart) stageModel.getPlayer2Board().getElement(rowindex, colindex);
                    p.setDestroyed();
                    destroyedShipPartsByPlayer1++;

                    update();
                    printSystemInstruction("Enemy hit !");
                    temporaryMessageForPlayer2 = "Your " + p.getParentShip().getName() + " was damaged during the previous turn !";
                    if (p.getParentShip().isShipDestroyed()) {
                        destroyedShipsByPlayer1++;
                        temporaryMessageForPlayer2 = "Your " + p.getParentShip().getName() + " was destroyed during the previous turn !";
                        printSystemInstruction(p.getParentShip().getName() + " enemy destroyed !");
                    }
                    stageModel.hidePlayer1Board();
                    stageModel.hidePlayer1AttackBoard();
                }
                player1AttackMarkIndex++;
                break;
            case 2:
                if (stageModel.getPlayer1Board().isEmptyAt(rowindex, colindex)) {
                    stageModel.getPlayer2AttackMarks(player2AttackMarkIndex).setColor(0);
                    stageModel.getPlayer2AttackBoard().addMarkToBoard(stageModel.getPlayer2AttackMarks(player2AttackMarkIndex), rowindex, colindex);
                    stageModel.getPlayer1Board().addMarkToBoard(stageModel.getPlayer2AttackMarks(player2AttackMarkIndex), rowindex, colindex);

                    update();
                    printSystemInstruction("Splash !");
                    stageModel.hidePlayer2Board();
                    stageModel.hideSecondAttackBoard();
                } else {
                    stageModel.getPlayer2AttackMarks(player2AttackMarkIndex).setColor(1);
                    stageModel.getPlayer2AttackBoard().addMarkToBoard(stageModel.getPlayer2AttackMarks(player2AttackMarkIndex), rowindex, colindex);
                    ShipPart p = (ShipPart) stageModel.getPlayer1Board().getElement(rowindex, colindex);
                    p.setDestroyed();
                    destroyedShipPartsByPlayer2++;

                    update();
                    printSystemInstruction("Enemy hit !");
                    temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + " was damaged during the previous turn !";
                    if (p.getParentShip().isShipDestroyed()) {
                        destroyedShipsByPlayer2++;
                        temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + "was destroyed during the previous turn !";
                        printSystemInstruction(p.getParentShip().getName() + " enemy destroyed !");
                    }
                    stageModel.hidePlayer2Board();
                    stageModel.hideSecondAttackBoard();
                }
                player2AttackMarkIndex++;
                break;
        }
        return true;
    }

    /**
     * Display the result of the game depending on who won and by which way
     */
    public void displayResults() {
        running = false;
        StageModel stageModel = (StageModel) model.getGameStage();
        if (stageModel.isPlayer2HasNoMoreShips()) {
            printSystemInstruction("Player 1 wins! (Player 2 has lost all his ships)");
        } else if (stageModel.isPlayer1HasNoMoreShips()) {
            printSystemInstruction("Player 2 wins! (Player 1 has lost all his ships)");
        } else if (player1AttackMarkIndex >= 35 && player2AttackMarkIndex >= 35) {
            printSystemInstruction("Both players have no more shells !");
            printSystemInstruction("Calculation of the winner...");
            int gagnant = calcWinner();
            switch (gagnant) {
                case 0:
                    if (model.getIdPlayer() == 1) {
                        model.setNextPlayer();
                    }
                    printSystemInstruction(model.getCurrentPlayerName() + " won !(destroyed more ships than his opponent)");
                    break;
                case 1:
                    if (model.getIdPlayer() == 0) {
                        model.setNextPlayer();
                    }
                    printSystemInstruction(model.getCurrentPlayerName() + " won !(destroyed more ships than his opponent)");
                    break;
                default:
                    printSystemInstruction("Draw! (both players hit the same number of squares and destroyed the same number of ships !");
                    break;
            }
        }
    }

    /**
     * Calcule the winner at the end of the game
     *
     * @return 1 or 2 depending on the player that has won
     */
    public int calcWinner() {
        if (destroyedShipPartsByPlayer1 > destroyedShipPartsByPlayer2) {
            return 0;
        } else if (destroyedShipPartsByPlayer2 > destroyedShipPartsByPlayer1) {
            return 1;
        }
        return calcWinnerIfEquality();
    }

    /**
     * Determine the winner if there's equality by counting the number of cases hit by each player
     *
     * @return 1 or 2 if depending on the player that has destroyed the most ships or 0 if there's equality
     */
    public int calcWinnerIfEquality() {
        if (destroyedShipsByPlayer1 > destroyedShipsByPlayer2) {
            return 0;
        } else if (destroyedShipsByPlayer2 > destroyedShipsByPlayer1) {
            return 1;
        }
        return 12;
    }

    /**
     * Utility method to set automatically ships to debug
     */
    public void setPlayerShipsDebug() {
        addShipAfterCheck(0, true, 0, 0);
        addShipAfterCheck(1, true, 0, 2);
        addShipAfterCheck(2, true, 0, 4);
        addShipAfterCheck(3, true, 0, 6);
        addShipAfterCheck(4, true, 0, 8);
        switchPlayer();
        addShipAfterCheck(0, true, 0, 0);
        addShipAfterCheck(1, true, 0, 2);
        addShipAfterCheck(2, true, 0, 4);
        addShipAfterCheck(3, true, 0, 6);
        addShipAfterCheck(4, true, 0, 8);
    }

    /**
     * method to automatically play to debug
     */
    public void playGameDebug() {
        StageModel stageModel = (StageModel) model.getGameStage();

        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(0), 1, 1);
        player1AttackMarkIndex++;
        stageModel.getPlayer1Board().addMarkToBoard(stageModel.getPlayer1AttackMarks(1), 1, 2);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(2), 1, 3);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(3), 1, 4);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(4), 1, 5);
        player1AttackMarkIndex++;

        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(5), 3, 1);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(6), 3, 2);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(7), 3, 3);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(8), 3, 4);
        player1AttackMarkIndex++;

        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(9), 5, 1);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(10), 5, 2);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(11), 5, 3);
        player1AttackMarkIndex++;

        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(12), 7, 1);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(13), 7, 2);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(14), 7, 3);
        player1AttackMarkIndex++;

        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(15), 9, 2);
        player1AttackMarkIndex++;
        stageModel.getPlayer1AttackBoard().addMarkToBoard(stageModel.getPlayer1AttackMarks(16), 9, 2);
        player1AttackMarkIndex++;
    }
}
