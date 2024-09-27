package view;

import boardifier.model.GameStageModel;
import boardifier.view.ClassicBoardLook;
import boardifier.view.GameStageView;
import model.AttackMark;
import model.Ship;
import model.ShipPart;
import model.StageModel;

/**
 * Class that add all the different objects look to the game
 */
public class StageView extends GameStageView {
    /**
     * the required constructor by BoardFire
     *
     * @param name           the name of the stage view
     * @param gameStageModel the game stage model where the look has to be added
     */
    public StageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
    }

    /**
     * Method that create all the look for every game element
     */
    @Override
    public void createLooks() {
        StageModel model = (StageModel) gameStageModel;

        addLook(new ClassicBoardLook(2, 6, model.getPlayer1Board(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 6, model.getPlayer2Board(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 6, model.getPlayer1AttackBoard(), 1, 1, true));
        addLook(new ClassicBoardLook(2, 6, model.getPlayer2AttackBoard(), 1, 1, true));

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
    }
}
