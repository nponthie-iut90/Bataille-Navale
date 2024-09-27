package control;

import boardifier.control.Controller;
import boardifier.control.ControllerMouse;
import boardifier.model.*;
import boardifier.view.GridLook;
import boardifier.view.View;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Board;
import model.StageModel;

import java.util.List;
import java.util.Optional;

public class ControllerBattleShipMouse extends ControllerMouse implements EventHandler<MouseEvent> {
    public ControllerBattleShipMouse(Model model, View view, Controller control) {
        super(model, view, control);
    }

    public Board defineBoard() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (model.getIdPlayer() == 0) {
            if (stageModel.getState() == StageModel.STATE_PLACE) {
                return stageModel.getPlayer1Board();
            } else {
                return stageModel.getPlayer1AttackBoard();
            }
        } else if (model.getIdPlayer() == 1) {
            if (stageModel.getState() == StageModel.STATE_PLACE) {
                return stageModel.getPlayer2Board();
            } else {
                return stageModel.getPlayer2AttackBoard();
            }
        }
        return null;
    }

    @Override
    public void handle(MouseEvent event) {
        StageModel stageModel = (StageModel) model.getGameStage();

        Board board = defineBoard();

        // if mouse event capture is disabled in the model, just return
        if (!model.isCaptureMouseEvent()) return;

        // get the clic x,y in the whole scene (this includes the menu bar if it exists)
        Coord2D clic = new Coord2D(event.getSceneX(), event.getSceneY());
        // get elements at that position
        List<GameElement> list = control.elementsAt(clic);

        if (stageModel.getState() == StageModel.STATE_PLACE) {
            GridLook lookBoard = (GridLook) control.getElementLook(board);
            int[] dest = lookBoard.getCellFromSceneLocation(clic);
            int x = dest[0];
            int y = dest[1];

            // Create an Alert with two buttons
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vertical or Horizontal ?");
            alert.setHeaderText("Vertical or Horizontal ?");

            // Create custom buttons
            ButtonType buttonTypeYes = new ButtonType("Horizontal");
            ButtonType buttonTypeNo = new ButtonType("Vertical");

            // Set the buttons on the alert
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            boolean horizontal = false;
            // Show the dialog and wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                horizontal = true;
            }

            if (stageModel.isShipWillBeInBoardAndLegal(stageModel.getGameVariant(), model.getIdPlayer(), stageModel.getShipIndex(model.getIdPlayer()), horizontal, y, x)) {
                if (stageModel.isShipPlacementOver()) {
                    stageModel.setState(StageModel.STATE_ATTACK);
                    control.endOfTurn();
                } else if (stageModel.getShipIndex(model.getIdPlayer()) == stageModel.getShipLimitIndex()) {
                    control.endOfTurn();
                }
            }
        } else if (stageModel.getState() == StageModel.STATE_ATTACK) {
            GridLook lookBoard = (GridLook) control.getElementLook(board);
            int[] dest = lookBoard.getCellFromSceneLocation(clic);
            if (stageModel.isPlayerHasAlreadyShotOnThisCase(model.getIdPlayer(), dest[0], dest[1])) {
                return;
            } else {
                String logText = stageModel.attack(model.getIdPlayer(), stageModel.getPlayerAttackMarkIndex(model.getIdPlayer()), dest[0], dest[1]);

                /*ActionList actionList = ActionFactory.generatePutInContainer(control, model, stageModel.defineAttackMarks(model.getIdPlayer()).get(stageModel.getPlayerAttackMarkIndex(model.getIdPlayer())), "player1attackboard", 8, 8);
                ActionList actionList1 = ActionFactory.generatePutInContainer(control, model, stageModel.defineAttackMarksForOwnBoard(model.getIdPlayer()).get(stageModel.getPlayerAttackMarkIndex(model.getIdPlayer())), "player2board", 8, 8);
                actionList.addAll(actionList1);
                actionList.setDoEndOfTurn(true);
                ActionPlayer actionPlayer = new ActionPlayer(model, control, actionList);
                actionPlayer.start();*/

                model.setCaptureMouseEvent(false);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    control.endOfTurn();
                    if (model.getCurrentPlayer().getType() == Player.COMPUTER) {
                        TextElement log = stageModel.getLog();
                        log.setText(logText);
                    }
                    model.setCaptureMouseEvent(true);
                });
                pause.play();
            }
        }
    }
}
