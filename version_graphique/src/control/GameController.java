package control;

import boardifier.control.Controller;
import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.view.View;
import model.StageModel;
import view.BattleShipStageView;
import view.GameView;

public class GameController extends Controller {
    public GameController(Model model, View view) {
        super(model, view);
        setControlKey(new ControllerBattleShipKey(model, view, this));
        setControlMouse(new ControllerBattleShipMouse(model, view, this));
        setControlAction (new ControllerBattleShipAction(model, view, this));
    }

    @Override
    public void endOfTurn() {
        StageModel stageModel = (StageModel) model.getGameStage();
        model.setNextPlayer();
        if (stageModel.isEndOfTheGame()) {
            stageModel.displayResults();
        }else {
            if (model.getIdPlayer() == 0) {
                stageModel.hidePlayer2Board();
                stageModel.showPlayer1Board();
                stageModel.hideSecondAttackBoard();
                stageModel.showPlayer1AttackBoard();
            } else if (model.getIdPlayer() == 1) {
                stageModel.hidePlayer1Board();
                stageModel.showSecondBoard();
                stageModel.hidePlayer1AttackBoard();
                stageModel.showSecondAttackBoard();
            }
            if (model.getCurrentPlayer().getType() == Player.COMPUTER && stageModel.getState() == StageModel.STATE_PLACE) {
                model.setCaptureEvents(false);
                int i = 0;
                while (i < stageModel.getShipLimitIndex()) {
                    playAI();
                    i++;
                }
                model.setCaptureEvents(true);
                if (stageModel.isShipPlacementOver()) {
                    stageModel.setState(StageModel.STATE_ATTACK);
                }
                endOfTurn();
            }
            if (model.getCurrentPlayer().getType() == Player.COMPUTER && stageModel.getState() == StageModel.STATE_ATTACK) {
                attackAI();
                endOfTurn();
            }

        }
    }

    private void playAI() {
        ChatGPT chatGPT = new ChatGPT(model, this);
        chatGPT.decide();
    }

    private void attackAI() {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (stageModel.getAiDifficulty() == 1) {
            ChatGPTToPlay chatGPTToPlay = new ChatGPTToPlay(model, this);
            chatGPTToPlay.decide();
        } else if (stageModel.getAiDifficulty() == 2) {
            CaiToPlay caiToPlay = new CaiToPlay(model, this);
            caiToPlay.decide();
        } else if (stageModel.getAiDifficulty() == 3) {
            AudrickGPTToPlay audrickGPTToPlay = new AudrickGPTToPlay(model, this);
            audrickGPTToPlay.decide();
        }
    }

    @Override
    public void startGame() throws GameException {
        StageModel stageModel = (StageModel) model.getGameStage();
        if (firstStageName.isEmpty()) throw new GameException("The name of the first stage have not been set. Abort");
        Logger.trace("START THE GAME");
        startStage(firstStageName);
        GameView battleShipView = (GameView) view;
        BattleShipStageView battleShipStageView = (BattleShipStageView) view.getGameStageView();
        battleShipView.showGameSetupDialog();
        model.setIdPlayer(1);
        endOfTurn();
    }
}
