package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.TextElement;
import boardifier.model.action.ActionList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import model.StageModel;

import java.util.Random;

public class ChatGPTToPlay extends Decider {
    public ChatGPTToPlay(Model model, Controller control) {
        super(model, control);
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    @Override
    public ActionList decide() {
        StageModel stageModel = (StageModel) model.getGameStage();
        int a = generateRandomNumber();
        int b = generateRandomNumber();
        while (stageModel.isPlayerHasAlreadyShotOnThisCase(model.getIdPlayer(), a, b)) {
            a = generateRandomNumber();
            b = generateRandomNumber();
        }
        String logText = stageModel.attack(model.getIdPlayer(), stageModel.getPlayerAttackMarkIndex(model.getIdPlayer()), a, b);
        stageModel.getLog().setText(logText);
        return new ActionList();
    }
}
