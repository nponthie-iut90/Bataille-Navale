package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.StageModel;

import java.util.Random;

public class ChatGPT extends Decider {
    int player = 0;
    int gameVariant = 0;

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

    StageModel stageModel = (StageModel) model.getGameStage();

    public ChatGPT(Model model, Controller control) {
        super(model, control);
    }

    public boolean generateRandomLetterHV() {
        Random rand = new Random();
        int num = rand.nextInt(2);
        return num == 0;
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    @Override
    public ActionList decide() {
        GameController gameController = (GameController) control;
        int shipToPlace = gameController.botShipIndex;
        defineCurrentPlayer();
        defineCurrentGameVariant();
        int H = generateRandomNumber();
        int A = generateRandomNumber();
        while (!stageModel.isShipWillBeInBoardAndLegal(generateRandomLetterHV(), H, A, shipToPlace, gameVariant, player)) {
            H = generateRandomNumber();
            A = generateRandomNumber();
        }
        return new ActionList();
    }
}