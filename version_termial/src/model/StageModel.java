package model;

import boardifier.model.GameStageModel;
import boardifier.model.Model;
import boardifier.model.StageElementsFactory;

import java.util.ArrayList;

/**
 * Class containing all the game elements for a game
 */
public class StageModel extends GameStageModel {
    /**
     * Required constructor by BoardFire for the stage model
     *
     * @param name  the name of the stage model
     * @param model the model to be given
     */
    public StageModel(String name, Model model) {
        super(name, model);
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
     * Calculate the available cases where a player can set one of his ships
     *
     * @param player the player to verify his grid
     * @return an array showing where the player can place his Ship
     */
    public int[][] calcValideCells(int player) {
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
        } else if (player == 2) {
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

    /**
     * Verify if the ship can be placed into the grid without being partially outside the grid
     *
     * @param horizontal if the ship have to be placed horizontally or vertically
     * @param colindex   the column index where the ship have to be placed
     * @param rowindex   the row index where the ship have to be placed
     * @param shipNumber the ship to being placed
     * @return true if the ship won't be outside the grid
     */
    public boolean isShipWillBeInBoardAndLegal(boolean horizontal, int colindex, int rowindex, int shipNumber, int gameVariant, int player) {
        StageModel stageModel = (StageModel) model.getGameStage();
        int[][] startShipIndex = calcOriginalCaseNeededByTheShip(colindex, rowindex);
        switch (gameVariant) {
            case 1:
                switch (player) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
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
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV1().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
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
                switch (player) {
                    case 1:
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (startShipIndex[i][j] == -1) {
                                    if (horizontal) {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (j + 1 >= 10) {
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer1ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
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
                                                return false;
                                            }
                                            startShipIndex[i][j + 1] = -1;
                                            j++;
                                        }
                                    } else {
                                        for (int k = 0; k < stageModel.getPlayer2ShipsV2().get(shipNumber).getSize() - 1; k++) {
                                            if (i + 1 >= 10) {
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
        return isInValideCells(startShipIndex, shipNumber, horizontal, colindex, rowindex, player, gameVariant);
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
    public boolean isInValideCells(int[][] shipFutureCoordinates, int shipNumber, boolean horizontal, int colindex, int rowindex, int player, int gameVariant) {
        StageModel stageModel = (StageModel) model.getGameStage();
        int[][] availableCoordinates = stageModel.calcValideCells(player);

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
                    return false;
                }
            }
        }
        addShipAfterCheck(shipNumber, horizontal, colindex, rowindex, gameVariant, player);
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
    public void addShipAfterCheck(int shipNumber, boolean horizontal, int colindex, int rowindex, int gameVariant, int player) {
        switch (gameVariant) {
            case 1:
                switch (player) {
                    case 1:
                        getPlayer1Board().addShipToBoard(getPlayer1ShipsV1().get(shipNumber), rowindex, colindex, horizontal);
                        break;
                    case 2:
                        getPlayer2Board().addShipToBoard(getPlayer2ShipsV1().get(shipNumber), rowindex, colindex, horizontal);
                        break;
                }
                break;
            case 2:
                switch (player) {
                    case 1:
                        getPlayer1Board().addShipToBoard(getPlayer1ShipsV2().get(shipNumber), rowindex, colindex, horizontal);
                        break;
                    case 2:
                        getPlayer2Board().addShipToBoard(getPlayer2ShipsV2().get(shipNumber), rowindex, colindex, horizontal);

                        break;
                }
                break;
        }
    }


    /**
     * Check if a player has already shot on a case
     *
     * @param rowindex the row index to check
     * @param colindex the column index to check
     * @param player   the player to check grid
     * @return true if the player has already shot on the case at given index
     */
    public boolean isPlayerHasAlreadyShotOnThisCase(int rowindex, int colindex, int player) {
        int[][] casesAlreadyShotsByPlayer = new int[10][10];
        switch (player) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (getPlayer1AttackBoard().isElementAt(i, j)) {
                            casesAlreadyShotsByPlayer[i][j] = 1;
                        }
                    }
                }
                return casesAlreadyShotsByPlayer[rowindex][colindex] == 1;
            case 2:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (getPlayer2AttackBoard().isElementAt(i, j)) {
                            casesAlreadyShotsByPlayer[i][j] = 1;
                        }
                    }
                }
                break;
        }
        return casesAlreadyShotsByPlayer[rowindex][colindex] == 1;
    }

    // work in progress, hard to put this method inside the model because of the management of game variables
    /*public String Attack(int rowindex, int colindex, int player, int gameVariant, int playerAttackMarkIndex) {
        switch (gameVariant) {
            case 1:
            switch (player) {
                case 1:
                    if (getPlayer2Board().isEmptyAt(rowindex, colindex)) {
                        getPlayer1AttackMarks(playerAttackMarkIndex).setColor(0);
                        getPlayer1AttackBoard().addMarkToBoard(getPlayer1AttackMarks(playerAttackMarkIndex), rowindex, colindex);
                        getPlayer2Board().addMarkToBoard(getPlayer1AttackMarks(playerAttackMarkIndex), rowindex, colindex);

                        /*hidePlayer1Board();
                        hidePlayer1AttackBoard();
                        return "Plouf";
                    } else {
                        getPlayer1AttackMarks(playerAttackMarkIndex).setColor(1);
                        getPlayer1AttackBoard().addMarkToBoard(getPlayer1AttackMarks(playerAttackMarkIndex), rowindex, colindex);
                        ShipPart p = (ShipPart) getPlayer2Board().getElement(rowindex, colindex);
                        p.setDestroyed();
                        //destroyedShipPartsByPlayer1++;

                    /*printSystemInstruction("Ennemi touché!");
                    if (p.getParentShip().isShipDestroyed()) {
                        destroyedShipsByPlayer1++;
                        printSystemInstruction("Bateau ennemi coulé !");
                    }
                    hidePlayer1Board();
                    hidePlayer1AttackBoard();
                        return "Ennemi touché !";
                    }
                    break;
                case 2:
                    if (getPlayer1Board().isEmptyAt(rowindex, colindex)) {
                        getPlayer2AttackMarks(playerAttackMarkIndex).setColor(0);
                        getPlayer2AttackBoard().addMarkToBoard(getPlayer2AttackMarks(playerAttackMarkIndex), rowindex, colindex);
                        getPlayer1Board().addMarkToBoard(getPlayer2AttackMarks(playerAttackMarkIndex), rowindex, colindex);

                        /*printSystemInstruction("Plouf");
                        hidePlayer2Board();
                        hideSecondAttackBoard();
                    } else {
                        getPlayer2AttackMarks(playerAttackMarkIndex).setColor(1);
                        getPlayer2AttackBoard().addMarkToBoard(getPlayer2AttackMarks(playerAttackMarkIndex), rowindex, colindex);
                        ShipPart p = (ShipPart) getPlayer1Board().getElement(rowindex, colindex);
                        p.setDestroyed();
                        //destroyedShipPartsByPlayer2++;

                        /*printSystemInstruction("Ennemi touché!");
                        if (p.getParentShip().isShipDestroyed()) {
                            destroyedShipsByPlayer2++;
                            printSystemInstruction("Bateau ennemi coulé !");
                        }
                        hidePlayer2Board();
                        hideSecondAttackBoard();
                    }
                    //player2AttackMarkIndex++;
                    break;
            }
        }
    }*/


    /**
     * Analyze the coordinate given by the player and shot on these coordinates if they are correct or the player didn't already fire on these coordinates
     *
     * @param rowindex the row index given by the player
     * @param colindex the column index given by the player
     * @return true if the attack could have take place on the given coordinates
     */
    public boolean analyzeAndAttack(int rowindex, int colindex, int player, int attackMarkIndex) {
        if ((isPlayerHasAlreadyShotOnThisCase(rowindex, colindex, player))) {
            System.out.println("Already shot here");
            return false;
        }
        attack(rowindex, colindex, player, attackMarkIndex);
        return true;
    }

    public String attack(int rowindex, int colindex, int player, int attackMarkIndex) {
        switch (player) {
            case 1:
                if (getPlayer2Board().isEmptyAt(rowindex, colindex)) {
                    getPlayer1AttackMarks(attackMarkIndex).setColor(0);
                    getPlayer1AttackBoard().addMarkToBoard(getPlayer1AttackMarks(attackMarkIndex), rowindex, colindex);
                    getPlayer2Board().addMarkToBoard(getPlayer1AttackMarks(attackMarkIndex), rowindex, colindex);

                } else {
                    getPlayer1AttackMarks(attackMarkIndex).setColor(1);
                    getPlayer1AttackBoard().addMarkToBoard(getPlayer1AttackMarks(attackMarkIndex), rowindex, colindex);
                    ShipPart p = (ShipPart) getPlayer2Board().getElement(rowindex, colindex);
                    p.setDestroyed();

                    if (p.getParentShip().isShipDestroyed()) {
                        return "Votre \" + p.getParentShip().getName() + \" a été détruit durant le tour précédant !";
                    } else {
                        return "Votre \" + p.getParentShip().getName() + \" a été endommagé durant le tour précédant !";
                    }
                }
                break;
            case 2:
                if (getPlayer1Board().isEmptyAt(rowindex, colindex)) {
                    getPlayer2AttackMarks(attackMarkIndex).setColor(0);
                    getPlayer2AttackBoard().addMarkToBoard(getPlayer2AttackMarks(attackMarkIndex), rowindex, colindex);
                    getPlayer1Board().addMarkToBoard(getPlayer2AttackMarks(attackMarkIndex), rowindex, colindex);
                } else {
                    getPlayer2AttackMarks(attackMarkIndex).setColor(1);
                    getPlayer2AttackBoard().addMarkToBoard(getPlayer2AttackMarks(attackMarkIndex), rowindex, colindex);
                    ShipPart p = (ShipPart) getPlayer1Board().getElement(rowindex, colindex);
                    p.setDestroyed();

                    if (p.getParentShip().isShipDestroyed()) {
                        return "Votre \" + p.getParentShip().getName() + \" a été détruit durant le tour précédant !";
                    } else {
                        return "Votre \" + p.getParentShip().getName() + \" a été endommagé durant le tour précédant !";
                    }
                }
                break;
        }
        hidePlayer1Board();
        hidePlayer2Board();
        hidePlayer1AttackBoard();
        hideSecondAttackBoard();
        return "erreur";
    }


    /**
     * Calculate if the player 1 has lost all of his ships
     *
     * @return true if the player 1 has lost all of his ships
     */
    public boolean isPlayer1HasNoMoreShips() {
        for (Ship ship : player1ShipsV1) {
            if (!ship.isShipDestroyed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculate if the player 2 has lost all of his ships
     *
     * @return true if the player 2 has lost all of his ships
     */
    public boolean isPlayer2HasNoMoreShips() {
        for (Ship ship : player2ShipsV1) {
            if (!ship.isShipDestroyed()) {
                return false;
            }
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
}
