package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.ShipPart;

/**
 * Class that represent the look of a ship part
 */
public class ShipPartLook extends ElementLook {
    private Circle circle;

    /**
     * the required constructor by BoardFire
     *
     * @param element the element to be added
     */
    public ShipPartLook(GameElement element) {
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
     * the way the ship part has to be rendered depending on its color and if the ship part is destroyed or not
     */
    @Override
    protected void render() {
        ShipPart shipPart = (ShipPart) element;
        circle = new Circle(10);
        if (shipPart.isDestroyed()) {
            if (shipPart.getColor() == 1) {
                Line line1 = new Line(-8, -8, 8, 8);
                line1.setStrokeWidth(2);
                line1.setStroke(Color.BLUE);
                Line line2 = new Line(8, -8, -8, 8);
                line2.setStrokeWidth(2);
                line2.setStroke(Color.BLUE);
                addShape(line1);
                addShape(line2);
            } else {
                Line line1 = new Line(-8, -8, 8, 8);
                line1.setStrokeWidth(2);
                line1.setStroke(Color.RED);
                Line line2 = new Line(8, -8, -8, 8);
                line2.setStrokeWidth(2);
                line2.setStroke(Color.RED);
                addShape(line1);
                addShape(line2);
            }
        } else if (shipPart.getColor() == 1) {
            circle.setFill(Color.BLUE);
            addShape(circle);
        } else {
            circle.setFill(Color.RED);
            addShape(circle);
        }


    }
}
