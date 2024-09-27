package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

/**
 * Class that represent a ship part of a ship
 */
public class ShipPart extends GameElement {
    /**
     * the ship that owns the ship part
     */
    private final Ship parentShip;
    /**
     * if the ship part is destroyed or not
     */
    private boolean isDestroyed;
    /**
     * the color of the ship part
     */
    private int color;

    /**
     * Constructor for a ship part
     *
     * @param color          the color of the ship part
     * @param parentShip     the ship that owns the ship part
     * @param gameStageModel the gale stage model where the ship part has to be added
     */
    public ShipPart(int color, Ship parentShip, GameStageModel gameStageModel) {
        super(gameStageModel);
        ElementTypes.register("shipPart", 50);
        type = ElementTypes.getType("shipPart");
        this.isDestroyed = false;
        this.color = color;
        this.parentShip = parentShip;
    }

    /**
     * Access if the ship part is destroyed or not
     *
     * @return true if the ship par is destroyed
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * set the ship part destroyed
     */
    public void setDestroyed() {
        isDestroyed = true;
    }

    /**
     * Access the color of the ship part
     *
     * @return the color of the ship part
     */
    public int getColor() {
        return color;
    }

    /**
     * Change the color and style of the ship part
     *
     * @param shipDestroyed true if the ship has to be set destroyed, false else
     */
    public void setShipDestroyed(boolean shipDestroyed) {
        this.isDestroyed = shipDestroyed;
    }

    /**
     * Access the ship that owns the ship part
     *
     * @return the ship that owns the ship part
     */
    public Ship getParentShip() {
        return parentShip;
    }

    @Override
    public String toString() {
        return "ShipPart{" +
                "parentShip=" + parentShip +
                ", isDestroyed=" + isDestroyed +
                ", color=" + color +
                '}';
    }
}
