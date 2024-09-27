package view;

import boardifier.model.GameElement;
import boardifier.view.ConsoleColor;
import boardifier.view.ElementLook;
import model.AttackMark;

/**
 * Class that define the look of an attack mark
 */
public class AttackMarkLook extends ElementLook {
    /**
     * the required constructor by BoardFire
     *
     * @param element the element to be added
     */
    public AttackMarkLook(GameElement element) {
        super(element, 1, 1);
    }

    /**
     * the way the attack mark has to be rendered depending on its color
     */
    protected void render() {
        AttackMark attackMark = (AttackMark) element;
        if (attackMark.getColor() == 1) {
            shape[0][0] = ConsoleColor.RED + "X" + ConsoleColor.RESET;
        } else {
            shape[0][0] = ConsoleColor.WHITE + "X" + ConsoleColor.RESET;
        }
    }
}
