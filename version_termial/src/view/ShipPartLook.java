package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.ShipPart;

/**
 * Class that represent the look of a ship part
 */
public class ShipPartLook extends ElementLook {
    /**
     * the required constructor by BoardFire
     *
     * @param element the element to be added
     */
    public ShipPartLook(GameElement element) {
        super(element, 1, 1);
    }

    /**
     * the way the ship part has to be rendered depending on its color and if the ship part is destroyed or not
     */
    protected void render() {
        ShipPart shipPart = (ShipPart) element;
        if (shipPart.getColor() == 1) {
            shape[0][0] = ConsoleColor.BLUE + "O" + ConsoleColor.RESET;
            if (shipPart.isDestroyed()) {
                shape[0][0] = ConsoleColor.BLUE + "X" + ConsoleColor.RESET;
            }
        } else {
            shape[0][0] = ConsoleColor.RED + "O" + ConsoleColor.RESET;
            if (shipPart.isDestroyed()) {
                shape[0][0] = ConsoleColor.RED + "X" + ConsoleColor.RESET;
            }
        }
    }
}
