package view;

import boardifier.model.GameStageModel;
import boardifier.view.ClassicBoardLook;
import boardifier.view.GameStageView;
import boardifier.view.TextLook;
import javafx.scene.paint.Color;
import model.AttackMark;
import model.Ship;
import model.ShipPart;
import model.StageModel;

public class BattleShipStageView extends GameStageView {
    public BattleShipStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
    }

    /**
     * Method that create all the look for every game element
     */
    @Override
    public void createLooks() {
        StageModel model = (StageModel) gameStageModel;

        addLook(new ClassicBoardLook(50, model.getPlayer1Board(), 0, Color.LIGHTBLUE, Color.LIGHTBLUE, 0, Color.BLACK, 5, Color.BLUE, true));
        addLook(new ClassicBoardLook(50, model.getPlayer2Board(), 0, Color.LIGHTBLUE, Color.LIGHTBLUE, 0, Color.BLACK, 5, Color.RED, true));
        addLook(new ClassicBoardLook(50, model.getPlayer1AttackBoard(), 0, Color.LIGHTBLUE, Color.LIGHTBLUE, 0, Color.BLACK, 5, Color.BLUE, true));
        addLook(new ClassicBoardLook(50, model.getPlayer2AttackBoard(), 0, Color.LIGHTBLUE, Color.LIGHTBLUE, 0, Color.BLACK, 5, Color.RED, true));

        for (Ship ship : model.getPlayer1ShipsV1()) {
            for (ShipPart shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        for (Ship ship : model.getPlayer2ShipsV1()) {
            for (ShipPart shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        for (Ship ship : model.getPlayer1ShipsV2()) {
            for (ShipPart shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        for (Ship ship : model.getPlayer2ShipsV2()) {
            for (ShipPart shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        for (AttackMark attackMark : model.getPlayer1AttackMarks()) {
            addLook(new AttackMarkLook(attackMark));
        }

        for (AttackMark attackMark : model.getPlayer2AttackMarks()) {
            addLook(new AttackMarkLook(attackMark));
        }

        for (AttackMark attackMark : model.getPlayer1AttackMarksForOwnBoard()) {
            addLook(new AttackMarkLook(attackMark));
        }

        for (AttackMark attackMark : model.getPlayer2AttackMarksForOwnBoard()) {
            addLook(new AttackMarkLook(attackMark));
        }

        addLook(new TextLook(20, "0x000000", model.getLog()));
    }
}
