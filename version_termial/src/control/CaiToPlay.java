package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.*;

import java.util.Random;

public class CaiToPlay extends Decider {
    int attackMarkIndex = 0;
    int player = 0;
    int gameVariant = 0;
    int playingMode = 1;
    int x;
    int y;

    public CaiToPlay(Model model, Controller control) {
        super(model, control);
    }

    public void defineCurrentPlayer() {
        if (model.getIdPlayer() == 0) {
            player = 1;
        } else {
            player = 2;
        }
    }

    int[][] matrixBoard = new int[10][10];

    /*
    //remplir matrice de 0
    for(int i = 0;i<10;i++){
        for (int j = 0; j < 10; j++) {
            matrixBoard[i][j] = 0;
        }
    }*/


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

    public int generateRandomNumberheadsortails() {
        Random rand = new Random();
        return rand.nextInt(2);
    }


    public void attackHunt() {
        GameController gameController = (GameController) control;
        int attackMarkIndex = 0;
        switch (player) {
            case 1:
                attackMarkIndex = gameController.player1AttackMarkIndex;
                break;
            case 2:
                attackMarkIndex = gameController.player2AttackMarkIndex;
                break;
        }
        StageModel stageModel = (StageModel) model.getGameStage();
        int a = generateRandomNumber();
        int b = generateRandomNumber();
        while (!stageModel.analyzeAndAttack(a, b, player, attackMarkIndex)) {
            a = generateRandomNumber();
            b = generateRandomNumber();
        }
        x = a;
        y = b;
    }


    public void definePlayingMode() {
        StageModel stageModel = (StageModel) model.getGameStage();
        // chercher sur la grille du joueur adverse si un shipPart est touché sans que son Ship parent ne soit détruit
        // dans ce cas mode de jeu = attaque sinon random
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (player) {
                    case 1:
                        if (stageModel.getPlayer2Board().getElement(i, j) instanceof ShipPart) {
                            if (((ShipPart) stageModel.getPlayer2Board().getElement(i, j)).isDestroyed() && (!(((ShipPart) stageModel.getPlayer2Board().getElement(i, j)).getParentShip().isShipDestroyed()))) {
                                playingMode = 2;
                                return;
                            } else {
                                playingMode = 1;
                            }
                            break;
                        }
                    case 2:
                        if (stageModel.getPlayer1Board().getElement(i, j) instanceof ShipPart) {
                            if (((ShipPart) stageModel.getPlayer1Board().getElement(i, j)).isDestroyed() && (!(((ShipPart) stageModel.getPlayer1Board().getElement(i, j)).getParentShip().isShipDestroyed()))) {
                                playingMode = 2;
                                return;
                            } else {
                                playingMode = 1;
                            }
                            break;
                        }
                }
            }
        }
    }

    public void casesAlreadyHit() {
        // looks at the computer's attack board and checks for all the boxes it has already shot in
        // sets them as a -1 on matrixBoard
        StageModel stageModel = (StageModel) model.getGameStage();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (player) {
                    case 1:
                        if (stageModel.getPlayer1AttackBoard().getElement(i, j) instanceof AttackMark) {
                            matrixBoard[i][j] = -1;
                        }
                    case 2:
                        if (stageModel.getPlayer2AttackBoard().getElement(i, j) instanceof AttackMark) {
                            matrixBoard[i][j] = -1;
                        }
                }
            }
        }
    }


    public void boatsAlreadyHit() {
        // Check the opponent's board to see if there are any ships already sunk
        // Set all adjacent cells to a sunken ship to -1 on matrixBoard

        StageModel stageModel = (StageModel) model.getGameStage();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (player) {
                    case 1:
                        if (stageModel.getPlayer2Board().getElement(i, j) instanceof ShipPart) {
                            ShipPart p = (ShipPart) stageModel.getPlayer2Board().getElement(i, j);
                            if (p.isDestroyed()) {
                                if (p.getParentShip().isShipDestroyed()) {
                                    if (j - 1 >= 0) {
                                        matrixBoard[i][j - 1] = -1;
                                    }
                                    matrixBoard[i][j] = -1;
                                    if (j + 1 <= 9) {
                                        matrixBoard[i][j + 1] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i + 1 <= 9)) {
                                        matrixBoard[i + 1][j - 1] = -1;
                                    }
                                    if (i + 1 <= 9) {
                                        matrixBoard[i + 1][j] = -1;
                                    }
                                    if ((j + 1 <= 9) && (i + 1 <= 9)) {
                                        matrixBoard[i + 1][j + 1] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i - 1 >= 0)) {
                                        matrixBoard[i - 1][j - 1] = -1;
                                    }
                                    if (j - 1 >= 0) {
                                        matrixBoard[i - 1][j] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i - 1 >= 0)) {
                                        matrixBoard[i - 1][j + 1] = -1;
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        if (stageModel.getPlayer1Board().getElement(i, j) instanceof ShipPart) {
                            ShipPart p = (ShipPart) stageModel.getPlayer1Board().getElement(i, j);
                            if (p.isDestroyed()) {
                                if (p.getParentShip().isShipDestroyed()) {
                                    if (j - 1 >= 0) {
                                        matrixBoard[i][j - 1] = -1;
                                    }
                                    matrixBoard[i][j] = -1;
                                    if (j + 1 <= 9) {
                                        matrixBoard[i][j + 1] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i + 1 <= 9)) {
                                        matrixBoard[i + 1][j - 1] = -1;
                                    }
                                    if (i + 1 <= 9) {
                                        matrixBoard[i + 1][j] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i + 1 <= 9)) {
                                        matrixBoard[i + 1][j + 1] = -1;
                                    }
                                    if ((j - 1 >= 0) && (i - 1 >= 0)) {
                                        matrixBoard[i - 1][j - 1] = -1;
                                    }
                                    if (i - 1 >= 0) {
                                        matrixBoard[i - 1][j] = -1;
                                    }
                                    if ((i - 1 >= 0) && (j + 1 <= 9)) {
                                        matrixBoard[i - 1][j + 1] = -1;
                                    }
                                }
                            }
                        }
                }
            }
        }
    }


    public boolean isShipHurt() {
        // chercher sur la grille du joueur adverse quelles shipPart sont touchés sans que son Ship parent ne soit détruit
        // retourner tous les ship parts du meme navire déja détruites
        StageModel stageModel = (StageModel) model.getGameStage();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (player) {
                    case 1:
                        if (stageModel.getPlayer2Board().getElement(i, j) instanceof ShipPart) {
                            if (((ShipPart) stageModel.getPlayer2Board().getElement(i, j)).isDestroyed() && (!(((ShipPart) stageModel.getPlayer2Board().getElement(i, j)).getParentShip().isShipDestroyed()))) {
                                matrixBoard[i][j] = 1;
                            }
                        }
                    case 2:
                        if (stageModel.getPlayer1Board().getElement(i, j) instanceof ShipPart) {
                            if (((ShipPart) stageModel.getPlayer1Board().getElement(i, j)).isDestroyed() && (!(((ShipPart) stageModel.getPlayer1Board().getElement(i, j)).getParentShip().isShipDestroyed()))) {
                                matrixBoard[i][j] = 1;
                            }
                        }
                }
            }
        }
        return false;
    }

    public void attackAttack() {
        // Find the coordinates to attack and perform the attack
        // Can use the matrix
        // If the cell is 0, we don't know what's there
        // If the cell is -1, we are sure there is nothing
        // If the cell is 1, there is a part of a ship and the ship is not completely sunk
        //attackAttack will attack adjacent to boxes that have a 1
        StageModel stageModel = (StageModel) model.getGameStage();
        GameController gameController = (GameController) control;

        casesAlreadyHit();
        boatsAlreadyHit();
        isShipHurt();

        int targetX = -1;
        int targetY = -1;
        boolean isTargetValide = false;
        int count = 2;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrixBoard[i][j] == 1) {
                    if ((j + 1 <= 9) && (matrixBoard[i][j + 1] == 1)) {
                        targetX = i;
                        while (!isTargetValide) {
                            if ((j + count <= 9) && (matrixBoard[i][j + count] == 1)) {
                                count++;
                                continue;
                            } else if (j + count > 9 || matrixBoard[i][j + count] == (-1)) {
                                targetY = j - 1;
                                isTargetValide = true;
                            } else if (matrixBoard[i][j + count] == 0) {
                                targetY = j;
                                isTargetValide = true;
                            }
                            count++;
                        }
                    } else if ((i + 1 <= 9) && (matrixBoard[i + 1][j] == 1)) {
                        targetY = j;
                        while (!isTargetValide) {
                            if ((i + count <= 9) && (matrixBoard[i + count][j] == 1)) {
                                count++;
                                continue;
                            } else if (i + count > 9 || matrixBoard[i + count][j] == (-1)) {
                                targetX = i - 1;
                                isTargetValide = true;
                            } else if (matrixBoard[i + count][j] == 0) {
                                targetX = i;
                                isTargetValide = true;
                            }
                            count++;
                        }
                    } else {
                        if ((j + 1 <= 9) && (matrixBoard[i][j + 1] == 0)) {
                            targetX = i;
                            targetY = j + 1;
                        } else if ((i + 1 <= 9) && (matrixBoard[i + 1][j] == 0)) {
                            targetX = i + 1;
                            targetY = j;
                        } else if ((j - 1 >= 0) && (matrixBoard[i][j - 1] == 0)) {
                            targetX = i;
                            targetY = j - 1;
                        } else if ((i - 1 >= 0) && (matrixBoard[i - 1][j] == 0)) {
                            targetX = i - 1;
                            targetY = j;
                        }
                    }
                }
            }
        }
        int attackMarkIndex = 0;
        switch (player) {
            case 1:
                attackMarkIndex = gameController.player1AttackMarkIndex;
                break;
            case 2:
                attackMarkIndex = gameController.player2AttackMarkIndex;
                break;
        }
        stageModel.analyzeAndAttack(targetX, targetY, player, attackMarkIndex);
        x = targetX;
        y = targetY;
    }

    @Override
    public ActionList decide() {
        defineCurrentPlayer();
        defineCurrentGameVariant();
        definePlayingMode();
        GameController gameController = (GameController) control;
        StageModel stageModel = (StageModel) model.getGameStage();
        switch (player) {
            case 1:
                attackMarkIndex = gameController.player1AttackMarkIndex;
                break;
            case 2:
                attackMarkIndex = gameController.player2AttackMarkIndex;
                break;
        }


        if (playingMode == 1) {
            attackHunt();
        } else if (playingMode == 2) {
            attackAttack();
        }
            /*
            int a = generateRandomNumber();
            int b = generateRandomNumber();
            while (!stageModel.analyzeAndAttack(a, b, player, attackMarkIndex)) {
                a = generateRandomNumber();
                b = generateRandomNumber();
            }*/

        //((GameController) control).temporaryMessage = stageModel.attack(a, b, player, attackMarkIndex);
        if (player == 1) {
            gameController.player1AttackMarkIndex++;
            if (stageModel.getPlayer2Board().isElementAt(x, y)) {
                if (stageModel.getPlayer2Board().getElement(x, y) instanceof ShipPart) {
                    ShipPart p = (ShipPart) stageModel.getPlayer2Board().getElement(x, y);
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
            if (stageModel.getPlayer1Board().isElementAt(x, y)) {
                if (stageModel.getPlayer1Board().getElement(x, y) instanceof ShipPart) {
                    ShipPart p = (ShipPart) stageModel.getPlayer1Board().getElement(x, y);
                    gameController.destroyedShipPartsByPlayer2++;
                    gameController.temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + " has been hit during last turn !";
                    if (p.getParentShip().isShipDestroyed()) {
                        gameController.destroyedShipsByPlayer2++;
                        gameController.temporaryMessageForPlayer1 = "Your " + p.getParentShip().getName() + " has been destroyed during last turn !";
                    }
                }
            }
        }
        //ActionList actionList = ActionFactory.generatePutInContainer(model, AttackMark, "bataille navale", a, b);
        //actionList.setDoEndOfTurn(true);
        return new ActionList();
    }
}
