package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        super(element);
    }

    @Override
    public void onSelectionChange() {

    }

    @Override
    public void onFaceChange() {
        clearGroup();
        render();
    }

    /**
     * the way the attack mark has to be rendered depending on its color
     */
    @Override
    protected void render() {
        AttackMark attackMark = (AttackMark) element;
        Line line1 = new Line(-10, 0, 10, 0);
        Line line2 = new Line(0, -10, 0, 10);
        if (attackMark.getColor() == 1) {
            line1.setStroke(Color.RED);
            line1.setStrokeWidth(2);
            line2.setStroke(Color.RED);
            line2.setStrokeWidth(2);
        } else if (attackMark.getColor() == 0){
            line1.setStroke(Color.WHITE);
            line1.setStrokeWidth(2);
            line2.setStroke(Color.WHITE);
            line2.setStrokeWidth(2);
        }
        addShape(line1);
        addShape(line2);
    }
}
