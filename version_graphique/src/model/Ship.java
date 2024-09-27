package model;

import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

/**
 * Class that represent a ship
 */
public class Ship extends GameElement {
    /**
     * the name of the ship
     */
    private final String name;
    /**
     * the size of the ship
     */
    private final int size;
    /**
     * if the ship is destroyed or not
     */
    private boolean isShipDestroyed;
    /**
     * the array that contains all the ship parts of the ship
     */
    private final ShipPart[] ShipParts;

    /**
     * Constructor to create a ship and his ship parts
     *
     * @param gameStageModel the game stage model where the ship has to be added
     * @param size           the length of the ship
     * @param name           the name of the ship
     * @param color          the color of the ship
     */
    public Ship(GameStageModel gameStageModel, int size, String name, int color) {
        super(gameStageModel);
        this.size = size;
        this.name = name;
        this.ShipParts = new ShipPart[size];
        this.isShipDestroyed = false;
        for (int i = 0; i < getSize(); i++) {
            ShipPart shipPart = new ShipPart(color, this, getGameStage());
            setShipParts(i, shipPart);
            gameStageModel.addElement(shipPart);
        }
    }

    /**
     * Access the state of the ship
     *
     * @return the state of the ship
     */
    public boolean getIsShipDestroyed() {
        return this.isShipDestroyed;
    }

    /**
     * Access the name of the ship
     *
     * @return the name of the ship
     */
    public String getName() {
        return this.name;
    }

    /**
     * Access the size of the ship
     *
     * @return the size of the ship
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Access the ship parts of the ship
     *
     * @return the array containing the ship parts of the ship
     */
    public ShipPart[] getShipParts() {
        return this.ShipParts;
    }

    /**
     * Access to a ship part at a given index
     *
     * @param i the index to get the ship part
     * @return a ship part at the given index
     */
    public ShipPart getShipParts(int i) {
        return ShipParts[i];
    }

    /**
     * Add a ship part to the ship
     *
     * @param i        the index of the ship part in the array
     * @param shipPart the ship part to be added
     */
    public void setShipParts(int i, ShipPart shipPart) {
        ShipParts[i] = shipPart;
    }

    /**
     * method that verify if the ship is destroyed (all ship parts of the ship destroyed)
     *
     * @return true if all the ship parts of the ship are destroyed
     */
    public boolean isShipDestroyed() {
        for (ShipPart shipPart : ShipParts) {
            if (!shipPart.isDestroyed()) {
                return false;
            }
        }
        this.setShipDestroyed(true);
        return true;
    }

    /**
     * method that set the ship as destroyed or not
     *
     * @param shipDestroyed true if the ship has to be set destroyed
     */
    public void setShipDestroyed(boolean shipDestroyed) {
        isShipDestroyed = shipDestroyed;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", isShipDestroyed=" + isShipDestroyed +
                '}';
    }
}
