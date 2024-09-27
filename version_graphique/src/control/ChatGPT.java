package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.StageModel;

import java.util.Random;

public class ChatGPT extends Decider {
    StageModel stageModel = (StageModel) model.getGameStage();

    public ChatGPT(Model model, Controller control) {
        super(model, control);
    }

    public boolean generateRandomBoolean() {
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
        int H = generateRandomNumber();
        int A = generateRandomNumber();
        while (!stageModel.isShipWillBeInBoardAndLegal(stageModel.getGameVariant(), model.getIdPlayer(), stageModel.getShipIndex(model.getIdPlayer()), generateRandomBoolean(), H, A)) {
            H = generateRandomNumber();
            A = generateRandomNumber();
        }
        return new ActionList();
    }
}
