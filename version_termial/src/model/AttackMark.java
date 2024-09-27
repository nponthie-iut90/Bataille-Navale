package model;

import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

/**
 * Class that represent attack marks for the game attack part
 */
public class AttackMark extends GameElement {
    /**
     * the color of the attack mark
     */
    private int color;

    /**
     * Constructor to create an attack mark
     *
     * @param gameStageModel the stage model where the attack mark has to be added
     * @param color          the color of the attack mark
     */
    public AttackMark(GameStageModel gameStageModel, int color) {
        super(gameStageModel);
        this.color = color;
    }

    /**
     * Return the color of the attack mark
     *
     * @return the color of the attack mark
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the color of the attack mark
     *
     * @param color the color to be applied
     */
    public void setColor(int color) {
        this.color = color;
    }
}
