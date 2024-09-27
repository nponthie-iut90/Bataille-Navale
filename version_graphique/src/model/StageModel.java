package model;

import boardifier.model.*;

import java.util.ArrayList;

/**
 * Class containing all the game elements for a game
 */
public class StageModel extends GameStageModel {
    private int gameVariant = 1;
    public void setGameVariant(int gameVariant) {
        this.gameVariant = gameVariant;
        if (gameVariant == 1) {
            shipIndexLimit = 5;
        } else if (gameVariant == 2) {
            shipIndexLimit = 10;
        }
    }
    public int getGameVariant() {
        return gameVariant;
    }

    public int getShipIndexPlayer1() {
        return shipIndexPlayer1;
    }

    public int getShipIndexPlayer2() {
        return shipIndexPlayer2;
    }

    int shipIndexPlayer1 = 0;
    int shipIndexPlayer2 = 0;
    int shipIndexLimit = 0;
    public int getShipLimitIndex() {
        return shipIndexLimit;
    }
    public void setShipIndexLimit(int shipIndexLimit) {
        this.shipIndexLimit = shipIndexLimit;
    }
    public int getShipIndex(int player) {
        if (player == 0) {
            return shipIndexPlayer1;
        } else if (player == 1) {
            return shipIndexPlayer2;
        }
        return 0;
    }
    public void increaseShipIndex(int player) {
        if (player == 0) {
            shipIndexPlayer1++;
        } else if (player == 1) {
            shipIndexPlayer2++;
        }
    }
    public boolean isShipPlacementOver() {
        return (shipIndexPlayer1 == shipIndexLimit && shipIndexPlayer2 == shipIndexLimit);
    }
    private int player1AttackMarkIndex = 0;
    private int player2AttackMarkIndex = 0;
    public int getPlayerAttackMarkIndex(int player) {
        switch (player) {
            case 0:
                return player1AttackMarkIndex;
            case 1:
                return player2AttackMarkIndex;
        }
        return 0;
    }
    public void increasePlayerAttackMarkIndex(int player) {
        switch (player) {
            case 0:
                player1AttackMarkIndex++;
                break;
            case 1:
                player2AttackMarkIndex++;
                break;
        }
    }
    private ArrayList<AttackMark> player1AttackMarksForOwnBoard = new ArrayList<AttackMark>();
    public ArrayList<AttackMark> getPlayer1AttackMarksForOwnBoard() {
        return player1AttackMarksForOwnBoard;
    }
    public AttackMark getPlayer1AttackMarkForOwnBoard(int i) {
        return player1AttackMarksForOwnBoard.get(i);
    }
    private ArrayList<AttackMark> player2AttackMarksForOwnBoard = new ArrayList<AttackMark>();
    public ArrayList<AttackMark> getPlayer2AttackMarksForOwnBoard() {
        return player2AttackMarksForOwnBoard;
    }
    public AttackMark getPlayer2AttackMarkForOwnBoard(int i) {
        return player2AttackMarksForOwnBoard.get(i);
    }

    private int aiDifficulty;
    public int getAiDifficulty() {
        return aiDifficulty;
    }
    public void setAiDifficulty(int aiDifficulty) {
        this.aiDifficulty = aiDifficulty;
    }


    public final static int STATE_PLACE = 0;
    public final static int STATE_ATTACK = 2;


    /**
     * Required constructor by BoardFire for the stage model
     *
     * @param name  the name of the stage model
     * @param model the model to be given
     */
    public StageModel(String name, Model model) {
        super(name, model);
        setupCallbacks();
    }

    /**
     * The board of the player 1, where he has his ships
     */
    private Board player1Board;

    /**
     * The board of the player 2, where he has his ships
     */
    private Board player2Board;

    /**
     * The attack board of the player 1, where he put attack marks on
     */
    private Board player1AttackBoard;

    /**
     * The attack board of the player 2, where he put attack marks on
     */
    private Board player2AttackBoard;

    /**
     * Contains all player 1 Ships for the variant 1 game
     */
    private final ArrayList<Ship> player1ShipsV1 = new ArrayList<>();

    /**
     * Contains all player 2 Ships for the variant 1 game
     */
    private final ArrayList<Ship> player2ShipsV1 = new ArrayList<>();

    /**
     * Contains all player 1 Ships for the variant 2 game
     */
    private final ArrayList<Ship> player1ShipsV2 = new ArrayList<>();

    /**
     * Contains all player 2 Ships for the variant 2 game
     */
    private final ArrayList<Ship> player2ShipsV2 = new ArrayList<>();

    /**
     * Contains all player 1 attack marks, working for the two variants
     */
    private final ArrayList<AttackMark> player1AttackMarks = new ArrayList<>();

    /**
     * Contains all player 2 attack marks, working for the two variants
     */
    private final ArrayList<AttackMark> player2AttackMarks = new ArrayList<>();

    /**
     * Access the player 1 board
     *
     * @return the player 1 board
     */
    public Board getPlayer1Board() {
        return player1Board;
    }

    /**
     * Utility method to set a board to be the player 1 board while creating all game elements
     *
     * @param player1Board the player 1 board
     */
    protected void setPlayer1Board(Board player1Board) {
        this.player1Board = player1Board;
        addContainer(player1Board);
    }

    /**
     * Utility method to display the player 1 board
     */
    public void showPlayer1Board() {
        player1Board.setVisible(true);
    }

    /**
     * Utility method to hide the player 1 board
     */
    public void hidePlayer1Board() {
        player1Board.setVisible(false);
    }

    /**
     * Utility method to set a board to be the player 2 board while creating all game elements
     *
     * @param player2Board the player 2 board
     */
    protected void setPlayer2Board(Board player2Board) {
        this.player2Board = player2Board;
        addContainer(player2Board);
    }

    /**
     * Access the player 2 board
     *
     * @return the player 2 board
     */
    public Board getPlayer2Board() {
        return player2Board;
    }

    /**
     * Utility method to display the player 2 board
     */
    public void showSecondBoard() {
        player2Board.setVisible(true);
    }

    /**
     * Utility method to hide the player 2 board
     */
    public void hidePlayer2Board() {
        player2Board.setVisible(false);
    }

    /**
     * Utility method to set a board to be the player 1 attack board while creating all game elements
     *
     * @param player1AttackBoard the player 1 attack board
     */
    protected void setPlayer1AttackBoard(Board player1AttackBoard) {
        this.player1AttackBoard = player1AttackBoard;
        addContainer(player1AttackBoard);
    }

    /**
     * Access the player 1 attack board
     *
     * @return the player 1 attack board
     */
    public Board getPlayer1AttackBoard() {
        return player1AttackBoard;
    }

    /**
     * Utility method to display the player 1 attack board
     */
    public void showPlayer1AttackBoard() {
        player1AttackBoard.setVisible(true);
    }

    /**
     * Utility method to hide the player 1 attack board
     */
    public void hidePlayer1AttackBoard() {
        player1AttackBoard.setVisible(false);
    }

    /**
     * Utility method to set a board to be the player 2 attack board while creating all game elements
     *
     * @param player2AttackBoard the player 2 attack board
     */
    protected void setPlayer2AttackBoard(Board player2AttackBoard) {
        this.player2AttackBoard = player2AttackBoard;
        addContainer(player2AttackBoard);
    }

    /**
     * Access the player 2 attack board
     *
     * @return the player 2 attack board
     */
    public Board getPlayer2AttackBoard() {
        return player2AttackBoard;
    }

    /**
     * Utility method to display the player 2 attack board
     */
    public void showSecondAttackBoard() {
        player2AttackBoard.setVisible(true);
    }

    /**
     * Utility method to hide the player 2 attack board
     */
    public void hideSecondAttackBoard() {
        player2AttackBoard.setVisible(false);
    }

    /**
     * Access method to get the player 1 ships in game variant 1
     *
     * @return the ArrayList of player 1 ships in game variant 1
     */
    public ArrayList<Ship> getPlayer1ShipsV1() {
        return player1ShipsV1;
    }

    /**
     * Access method to get the player 2 ships in game variant 1
     *
     * @return the ArrayList of player 2 ships in game variant 1
     */
    public ArrayList<Ship> getPlayer2ShipsV1() {
        return player2ShipsV1;
    }

    /**
     * Access method to get the player 1 ships in game variant 2
     *
     * @return the ArrayList of player 1 ships in game variant 2
     */
    public ArrayList<Ship> getPlayer1ShipsV2() {
        return player1ShipsV2;
    }

    /**
     * Access method to get the player 2 ships in game variant 2
     *
     * @return the ArrayList of player 2 ships in game variant 2
     */
    public ArrayList<Ship> getPlayer2ShipsV2() {
        return player2ShipsV2;
    }

    /**
     * Access method to get the player 1 attack marks
     *
     * @return the ArrayList of player 1 attack marks
     */
    public ArrayList<AttackMark> getPlayer1AttackMarks() {
        return player1AttackMarks;
    }

    /**
     * Access method to get the player 1 attack mark at a certain index
     *
     * @param i the index of the AttackMark to get
     * @return the AttackMark of player 1 at the given index
     */
    public AttackMark getPlayer1AttackMarks(int i) {
        return player1AttackMarks.get(i);
    }

    /**
     * Access method to get the player 2 attack marks
     *
     * @return the ArrayList of player 2 attack marks
     */
    public ArrayList<AttackMark> getPlayer2AttackMarks() {
        return player2AttackMarks;
    }

    /**
     * Access method to get the player 2 attack mark at a certain index
     *
     * @param i the index of the AttackMark to get
     * @return the AttackMark of player 2 at the given index
     */
    public AttackMark getPlayer2AttackMarks(int i) {
        return player2AttackMarks.get(i);
    }

    /**
     * Calculate the available cases where a bot can set one of his ships
     *
     * @param player the player to verify his grid
     * @return an array showing where the bot can place his Ship
     */
    public int[][] calcValideCellsSpecialBot(int player) {
        int[][] valideCells = new int[10][10];
        if (player == 1) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (getPlayer1Board().isEmptyAt(i, j)) {
                        valideCells[i][j] = 1;
                    } else {
                        valideCells[i][j] = -1;
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (getPlayer2Board().isEmptyAt(i, j)) {
                        valideCells[i][j] = 1;
                    } else {
                        valideCells[i][j] = -1;
                    }
                }
            }
        }
        return valideCells;
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

    public void defineBoardsDependingOnCurrentPlayer() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (model.getIdPlayer() == 0) {
            stageModel.showPlayer1Board();
            stageModel.hidePlayer2Board();
            stageModel.showPlayer1AttackBoard();
            stageModel.hideSecondAttackBoard();
        } else if (model.getIdPlayer() == 1) {
            stageModel.hidePlayer1Board();
            stageModel.showSecondBoard();
            stageModel.hidePlayer1AttackBoard();
            stageModel.showSecondAttackBoard();
        }
    }

    /**
     * Calculate if the player 1 has lost all of his ships
     *
     * @return true if the player 1 has lost all of his ships
     */
    public boolean isPlayer1HasNoMoreShips() {
        switch (gameVariant) {
            case 1:
                for (Ship ship : player1ShipsV1) {
                    if (!ship.isShipDestroyed()) {
                        return false;
                    }
                }
                break;
            case 2:
                for (Ship ship : player1ShipsV2) {
                    if (!ship.isShipDestroyed()) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    /**
     * Calculate if the player 2 has lost all of his ships
     *
     * @return true if the player 2 has lost all of his ships
     */
    public boolean isPlayer2HasNoMoreShips() {
        switch (gameVariant) {
            case 1:
                for (Ship ship : player2ShipsV1) {
                    if (!ship.isShipDestroyed()) {
                        return false;
                    }
                }
                break;
            case 2:
                for (Ship ship : player2ShipsV2) {
                    if (!ship.isShipDestroyed()) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }

    /**
     * method of boardFire
     *
     * @return i don't know
     */
    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new StageModelFactory(this);
    }

    public Board defineBoard(int player) {
        switch (player) {
            case 0:
                return getPlayer1Board();
            case 1:
                return getPlayer2Board();
        }
        return null;
    }

    public Board defineAttackBoard(int player) {
        switch (player) {
            case 0:
                return player1AttackBoard;
            case 1:
                return player2AttackBoard;
        }
        return null;
    }

    public Board defineOpposantBoard(int player) {
        switch (player) {
            case 0:
                return player2Board;
            case 1:
                return player1Board;
        }
        return null;
    }

    public ArrayList<Ship> defineShips(int gameVariant, int player) {
        switch (gameVariant) {
            case 1:
                switch (player) {
                    case 0:
                        return getPlayer1ShipsV1();
                    case 1:
                        return getPlayer2ShipsV1();
                }
            case 2:
                switch (player) {
                    case 0:
                        return getPlayer1ShipsV2();
                    case 1:
                        return getPlayer2ShipsV2();
                }
        }
        return null;
    }

    /**
     * Verify if the ship can be placed into the grid without being partially outside the grid
     *
     * @param horizontal if the ship have to be placed horizontally or vertically
     * @param x          the column index where the ship have to be placed
     * @param y          the row index where the ship have to be placed
     * @param shipNumber the ship to being placed
     * @return true if the ship won't be outside the grid
     */
    public boolean isShipWillBeInBoardAndLegal(int gameVariant, int player, int shipNumber, boolean horizontal, int y, int x) {
        ArrayList<Ship> ships = defineShips(gameVariant, player);
        int[][] startShipIndex = calcOriginalCaseNeededByTheShip(y, x);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (startShipIndex[i][j] == -1) {
                    if (horizontal) {
                        for (int k = 0; k < ships.get(shipNumber).getSize() - 1; k++) {
                            if (j + 1 >= 10) {
                                getLog().setText("You cant place there ! The ship will go out of the board on the right");
                                return false;
                            }
                            startShipIndex[i][j + 1] = -1;
                            j++;
                        }
                    } else {
                        for (int k = 0; k < ships.get(shipNumber).getSize() - 1; k++) {
                            if (i + 1 >= 10) {
                                getLog().setText("You cant place there ! The ship will go out of the board on the bottom");
                                return false;
                            }
                            startShipIndex[i + 1][j] = -1;
                            i++;
                        }
                    }
                }
            }
        }
        return isInValideCells(gameVariant, player, shipNumber, startShipIndex, horizontal, y, x);
    }

    public boolean isInValideCells(int gameVariant, int player, int shipNumber, int[][] shipFutureCoordinates, boolean horizontal, int y, int x) {
        int[][] availableCoordinates = calcValideCells(player);

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
                    TextElement log = getLog();
                    log.setText("Invalid coordinate! You cannot place a ship on top of another ship or stick it to another ship!");
                    return false;
                }
            }
        }
        addShipAfterCheck(gameVariant, player, shipNumber, horizontal, y, x);
        return true;
    }

    public int[][] calcValideCells(int player) {
        int[][] valideCells = new int[10][10];
        Board board = defineBoard(player);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.isEmptyAt(i, j)) {
                    valideCells[i][j] = 1;
                } else {
                    valideCells[i][j] = -1;
                }
            }
        }
        return valideCells;
    }

    public void addShipAfterCheck(int gameVariant, int player, int shipNumber, boolean horizontal, int y, int x) {
        Board board = defineBoard(player);
        ArrayList<Ship> ships = defineShips(gameVariant, player);
        board.addShipToBoard(ships.get(shipNumber), x, y, horizontal);
        increaseShipIndex(model.getIdPlayer());
    }

    public ArrayList<AttackMark> defineAttackMarks(int player) {
        if (player == 0) {
            return player1AttackMarks;
        } else if (player == 1) {
            return player2AttackMarks;
        }
        return null;
    }

    public ArrayList<AttackMark> defineAttackMarksForOwnBoard(int player) {
        if (player == 0) {
            return player1AttackMarksForOwnBoard;
        } else if (player == 1) {
            return player2AttackMarksForOwnBoard;
        }
        return null;
    }

    public String attack(int player, int attackMarkIndex, int x, int y) {
        if (isPlayerHasAlreadyShotOnThisCase(player, x, y)) {
            return "You can not shot on a case you already shot!";
        }
        Board board = defineAttackBoard(player);
        Board opposantBoard = defineOpposantBoard(player);
        ArrayList<AttackMark> attackMarks = defineAttackMarks(player);
        ArrayList<AttackMark> attackMarks1 = defineAttackMarksForOwnBoard(player);
        if (opposantBoard.isEmptyAt(x, y)) {
            AttackMark attackMark = attackMarks.get(attackMarkIndex);
            attackMarks.get(attackMarkIndex).setColor(0);
            attackMark.addChangeFaceEvent();
            board.addMarkToBoard(attackMarks.get(attackMarkIndex), x, y);
            increasePlayerAttackMarkIndex(player);
            AttackMark attackMark1 = attackMarks1.get(attackMarkIndex);
            opposantBoard.addMarkToBoard(attackMark1, x, y);
            this.logText = "Plouf";
            TextElement log = getLog();
            log.setText(logText);
            return "No one of your ship has been hit during last turn !";
        } else {
            attackMarks.get(attackMarkIndex).setColor(1);
            board.addMarkToBoard(attackMarks.get(attackMarkIndex), x, y);
            ShipPart p = (ShipPart) opposantBoard.getElement(x, y);
            p.addChangeFaceEvent();
            p.setDestroyed();
            increasePlayerAttackMarkIndex(player);

            if (p.getParentShip().isShipDestroyed()) {
                this.logText = "You destroyed the enemy " + p.getParentShip().getName() + " !";
                TextElement log = getLog();
                log.setText(logText);
                increaseDestroyedShipPartsByPlayer(player);
                increaseDestroyedShipsByPlayer(player);
                /*if (AI && model.getCurrentPlayer().getType() == Player.HUMAN) {
                    return "";
                }*/
                return "Your " + p.getParentShip().getName() + " has been destroyed during last turn !";
            } else {
                this.logText = "You damaged the enemy " + p.getParentShip().getName() + " !";
                TextElement log = getLog();
                log.setText(logText);
                increaseDestroyedShipPartsByPlayer(player);
                /*if (AI && model.getCurrentPlayer().getType() == Player.HUMAN) {
                    return "";
                }*/
                return "Your " + p.getParentShip().getName() + " has been damaged during last turn !";
            }
        }
    }

    private boolean AI = true;
    public void setAI() {
        AI = true;
    }

    public boolean isPlayerHasAlreadyShotOnThisCase(int player, int x, int y) {
        int[][] casesAlreadyShotsByPlayer = new int[10][10];
        Board board = defineAttackBoard(player);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.isElementAt(i, j)) {
                    casesAlreadyShotsByPlayer[i][j] = 1;
                }
            }
        }
        return casesAlreadyShotsByPlayer[x][y] == 1;
    }

    private TextElement log;
    public void setLog(TextElement log) {
        this.log = log;
        addElement(log);
    }
    public TextElement getLog() {
        return log;
    }
    private String logText = "";
    public String getLogText() {
        return logText;
    }
    public void setLogText(String logText) {
        this.logText = logText;
    }

    public void displayPlaceInstruction(Ship ship) {
        log.setText("Place your " + ship.getName() + ", size = " + ship.getSize());
    }

    private void setupCallbacks() {
        onPutInContainer((element, gridDest, rowDest, colDest) -> {
            switch(getGameVariant()) {
                case 1:
                    if (getState() == StageModel.STATE_PLACE && getShipIndex(model.getIdPlayer()) + 1 != 5) {
                        displayPlaceInstruction(defineShips(getGameVariant(), model.getIdPlayer()).get(getShipIndex(model.getIdPlayer())+1));
                    } else if (getState() == StageModel.STATE_PLACE) {
                        displayOriginalPlaceInstruction();
                    }
                    break;
                case 2:
                    if (getState() == StageModel.STATE_PLACE && getShipIndex(model.getIdPlayer()) + 1 != 10) {
                        displayPlaceInstruction(defineShips(getGameVariant(), model.getIdPlayer()).get(getShipIndex(model.getIdPlayer())+1));
                    } else if (getState() == StageModel.STATE_PLACE) {
                        displayOriginalPlaceInstruction();
                    }
                    break;
            }


            /*if (isEndOfTheGame()) {
                displayResults();
            }*/
        });
    }

    public void displayOriginalPlaceInstruction() {
        getLog().setText("Place your aircraft carrier, size = 5");
    }

    public void displayResults() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (stageModel.isPlayer2HasNoMoreShips()) {
            model.setIdWinner(0);
            //printSystemInstruction("Player 1 wins! (Player 2 has lost all his ships)");
        } else if (stageModel.isPlayer1HasNoMoreShips()) {
            model.setIdWinner(1);
            //printSystemInstruction("Player 2 wins! (Player 1 has lost all his ships)");
        } else {
            //printSystemInstruction("Both players have no more shells !");
            //printSystemInstruction("Calculation of the winner...");
            int gagnant = calcWinner();
            switch (gagnant) {
                case 0:
                    if (model.getIdPlayer() == 1) {
                        model.setNextPlayer();
                    }
                    model.setIdWinner(0);
                    //printSystemInstruction(model.getCurrentPlayerName() + " won !(destroyed more ships than his opponent)");
                    break;
                case 1:
                    if (model.getIdPlayer() == 0) {
                        model.setNextPlayer();
                    }
                    model.setIdWinner(1);
                    //printSystemInstruction(model.getCurrentPlayerName() + " won !(destroyed more ships than his opponent)");
                    break;
                default:
                    model.setIdWinner(-1);
                    //printSystemInstruction("Draw! (both players hit the same number of squares and destroyed the same number of ships !");
                    break;
            }
        }
        model.stopGame();
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
     * Verify if the game is over
     *
     * @return true if one of the player has no more ship or if player have fired all of their shells
     */
    public boolean isEndOfTheGame() {
        return isPlayer1HasNoMoreShips() || isPlayer2HasNoMoreShips() || (player1AttackMarkIndex >= shellLimit && player2AttackMarkIndex >= shellLimit);
    }

    private int shellLimit = 0;
    public void setShellLimit(int shellLimit) {
        this.shellLimit = shellLimit;
    }
    private int destroyedShipPartsByPlayer1;
    private int destroyedShipPartsByPlayer2;
    public void increaseDestroyedShipPartsByPlayer(int player) {
        switch (player) {
            case 0:
                destroyedShipPartsByPlayer1++;
                break;
            case 1:
                destroyedShipPartsByPlayer2++;
                break;
        }
    }

    private int destroyedShipsByPlayer1;
    private int destroyedShipsByPlayer2;
    public void increaseDestroyedShipsByPlayer(int player) {
        switch (player) {
            case 0:
                destroyedShipsByPlayer1++;
                break;
            case 1:
                destroyedShipsByPlayer2++;
                break;
        }
    }
}
