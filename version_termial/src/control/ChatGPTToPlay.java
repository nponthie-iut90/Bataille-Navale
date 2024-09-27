package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.ShipPart;
import model.StageModel;

import java.util.Random;

public class ChatGPTToPlay extends Decider {
    int player = 0;
    int gameVariant = 0;

    public ChatGPTToPlay(Model model, Controller control) {
        super(model, control);
    }

    public void defineCurrentPlayer() {
        if (model.getIdPlayer() == 0) {
            player = 1;
        } else {
            player = 2;
        }
    }

    public void defineCurrentGameVariant() {
        GameController gameController = (GameController) control;
        if (gameController.gameVariant == 1) {
            gameVariant = 1;
        } else {
            gameVariant = 2;
        }
    }

    public int generateRandomNumber() {
        Random rand = new Random();

        return rand.nextInt(10);
    }

    @Override
    public ActionList decide() {
        defineCurrentPlayer();
        defineCurrentGameVariant();
        GameController gameController = (GameController) control;
        StageModel stageModel = (StageModel) model.getGameStage();
        int attackMarkIndex = 0;
        switch (player) {
            case 1:
                attackMarkIndex = gameController.player1AttackMarkIndex;
                break;
            case 2:
                attackMarkIndex = gameController.player2AttackMarkIndex;
                break;
        }

        int a = generateRandomNumber();
        int b = generateRandomNumber();
        while (!stageModel.analyzeAndAttack(a, b, player, attackMarkIndex)) {
            a = generateRandomNumber();
            b = generateRandomNumber();
        }
        //((GameController) control).temporaryMessage = stageModel.attack(a, b, player, attackMarkIndex);
        if (player == 1) {
            gameController.player1AttackMarkIndex++;
            if (stageModel.getPlayer2Board().isElementAt(a, b)) {
                if (stageModel.getPlayer2Board().getElement(a, b) instanceof ShipPart) {
                    ShipPart p = (ShipPart) stageModel.getPlayer2Board().getElement(a, b);
                    gameController.destroyedShipPartsByPlayer1++;
                    gameController.temporaryMessageForPlayer2 = "Your " + p.getParentShip().getName() + " has been hit during last turn !";
                    if (p.getParentShip().isShipDestroyed()) {
                        gameController.destroyedShipsByPlayer1++;
                        gameController.temporaryMessageForPlayer2 = "Your " + p.getParentShip().getName() + " has been destroyed during last turn !";
                    }
                }
            }
        } else if (player == 2) {
            gameController.player2AttackMarkIndex++;
            if (stageModel.getPlayer1Board().isElementAt(a, b)) {
                if (stageModel.getPlayer1Board().getElement(a, b) instanceof ShipPart) {
                    ShipPart p = (ShipPart) stageModel.getPlayer1Board().getElement(a, b);
                    gameController.destroyedShipPartsByPlayer2++;
                    gameController.temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + " has been hit during last turn !";
                    if (p.getParentShip().isShipDestroyed()) {
                        gameController.destroyedShipsByPlayer2++;
                        gameController.temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + " has been destroyed during last turn !";
                    }
                }
            }
        }
        //ActionList actionList = ActionFactory.generatePutInContainer(model, attackMark, "bataille navale", a, b);
        //actionList.setDoEndOfTurn(true);
        return new ActionList();
    }
}
