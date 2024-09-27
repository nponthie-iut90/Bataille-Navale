package model;

import boardifier.model.ContainerElement;
import boardifier.model.GameStageModel;

/**
 * Class to create board of 10x10
 */
public class Board extends ContainerElement {
    /**
     * Constructor to create a board of 10x10
     *
     * @param x              the horizontal position of the board in the terminal
     * @param y              the vertical position of the board in the terminal
     * @param gameStageModel the game stage model where to add the board
     */
    public Board(int x, int y, GameStageModel gameStageModel) {
        super("bataille navale", x, y, 10, 10, gameStageModel);
    }

    /**
     * Utility method to add a ship to a board
     *
     * @param ship       the ship to be added
     * @param x          the horizontal index of the ship
     * @param y          the vertical index of the ship
     * @param horizontal if the ship has to be added horizontally or vertically
     */
    public void addShipToBoard(Ship ship, int x, int y, boolean horizontal) {
        for (int i = 0; i < ship.getSize(); i++) {
            if (horizontal) {
                addElement(ship.getShipParts(i), x, y + i);
            } else {
                addElement(ship.getShipParts(i), x + i, y);
            }
        }
    }

    /**
     * Utility method to add an attack mark to a board
     *
     * @param attackMark the attack mark to be added to the board
     * @param x          the horizontal index to place the attack mark
     * @param y          the vertical index to place the attack mark
     */
    public void addMarkToBoard(AttackMark attackMark, int x, int y) {
        addElement(attackMark, x, y);
    }
}
