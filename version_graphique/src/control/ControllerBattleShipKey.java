package control;

import boardifier.control.Controller;
import boardifier.control.ControllerKey;
import boardifier.model.Model;
import boardifier.view.View;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ControllerBattleShipKey extends ControllerKey implements EventHandler<KeyEvent> {

    public ControllerBattleShipKey(Model model, View view, Controller control) {
        super(model, view, control);
    }

    @Override
    public void handle(KeyEvent event) {
        if (!model.isCaptureKeyEvent()) {
            return;
        }
    }
}
