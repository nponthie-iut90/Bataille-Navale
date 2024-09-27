package control;

import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.AttackMark;
import model.Board;
import model.ShipPart;
import model.StageModel;

import java.util.Random;

public class AudrickGPTToPlay extends Decider {
    int playingMode = 1;
    int x;
    int y;

    public AudrickGPTToPlay(Model model, Controller control) {
        super(model, control);
    }

    int[][] matrixBoard = new int[10][10];

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    public void attackHunt() {
        // Mettre à jour matrixBoard en fonction de l'état actuel du jeu
        casesAlreadyHit();
        shipsAlreadyHit();
        isShipHurt();

        StageModel stageModel = (StageModel) model.getGameStage();
        int attackMarkIndex = stageModel.getPlayerAttackMarkIndex(model.getIdPlayer());

        // Chercher une case aléatoire avec un 0 dans matrixBoard
        int a = -1;
        int b = -1;
        Random rand = new Random();

        do {
            a = rand.nextInt(10);
            b = rand.nextInt(10);
        } while (matrixBoard[a][b] != 0);

        // Effectuer l'attaque sur les coordonnées trouvées
        stageModel.attack(model.getIdPlayer(), attackMarkIndex, a, b);
        x = a;
        y = b;
    }



    public void definePlayingMode() {
        StageModel stageModel = (StageModel) model.getGameStage();
        // chercher sur la grille du joueur adverse si un shipPart est touché sans que son Ship parent ne soit détruit
        // dans ce cas mode de jeu = attaque sinon random
        Board board = stageModel.defineOpposantBoard(model.getIdPlayer());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getElement(i, j) instanceof ShipPart) {
                    if (((ShipPart) board.getElement(i, j)).isDestroyed() && (!(((ShipPart) board.getElement(i, j)).getParentShip().isShipDestroyed()))) {
                        playingMode = 2;
                        return;
                    } else {
                        playingMode = 1;
                    }
                }
            }
        }
    }

    public void casesAlreadyHit() {
        // looks at the computer's attack board and checks for all the boxes it has already shot in
        // sets them as a -1 on matrixBoard
        StageModel stageModel = (StageModel) model.getGameStage();
        Board attackBoard = stageModel.defineAttackBoard(model.getIdPlayer());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (attackBoard.getElement(i, j) instanceof AttackMark) {
                    matrixBoard[i][j] = -1;
                }
            }
        }
    }


    public void shipsAlreadyHit() {
        // Check the opponent's board to see if there are any ships already sunk
        // Set all adjacent cells to a sunken ship to -1 on matrixBoard
        StageModel stageModel = (StageModel) model.getGameStage();
        Board board = stageModel.defineOpposantBoard(model.getIdPlayer());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getElement(i, j) instanceof ShipPart) {
                    ShipPart p = (ShipPart) board.getElement(i, j);
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
                                matrixBoard[i][j - 1] = -1;
                            }
                            if ((j - 1 >= 0) && (i - 1 >= 0)) {
                                matrixBoard[i - 1][j - 1] = -1;
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
        Board board = stageModel.defineOpposantBoard(model.getIdPlayer());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getElement(i, j) instanceof ShipPart) {
                    if (((ShipPart) board.getElement(i, j)).isDestroyed() && (!(((ShipPart) board.getElement(i, j)).getParentShip().isShipDestroyed()))) {
                        matrixBoard[i][j] = 1;
                    }
                }
            }
        }
        return false;
    }

    public void attackAttack() {
        // Update matrixBoard based on the current game state
        casesAlreadyHit();
        shipsAlreadyHit();
        isShipHurt();

        StageModel stageModel = (StageModel) model.getGameStage();

        int targetX = -1;
        int targetY = -1;
        boolean isTargetValid = false;
        int count = 2;

        // Iterate through matrixBoard to find a ship part that is damaged but not fully sunk
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrixBoard[i][j] == 1) {
                    // If adjacent cells are also part of the ship, determine direction to continue attack
                    if (j + 1 <= 9 && matrixBoard[i][j + 1] == 1) {
                        // Continue attack to the right
                        targetX = i;
                        while (!isTargetValid) {
                            if (j + count <= 9 && matrixBoard[i][j + count] == 1) {
                                count++;
                                continue;
                            } else if (j + count > 9 || matrixBoard[i][j + count] == -1) {
                                targetY = j - 1;
                                isTargetValid = true;
                            } else if (matrixBoard[i][j + count] == 0) {
                                targetY = j + count;
                                isTargetValid = true;
                            }
                        }
                    } else if (i + 1 <= 9 && matrixBoard[i + 1][j] == 1) {
                        // Continue attack downwards
                        targetY = j;
                        while (!isTargetValid) {
                            if (i + count <= 9 && matrixBoard[i + count][j] == 1) {
                                count++;
                                continue;
                            } else if (i + count > 9 || matrixBoard[i + count][j] == -1) {
                                targetX = i - 1;
                                isTargetValid = true;
                            } else if (matrixBoard[i + count][j] == 0) {
                                targetX = i + count;
                                isTargetValid = true;
                            }
                        }
                    } else {
                        // Attack adjacent to the current ship part
                        if (j + 1 <= 9 && matrixBoard[i][j + 1] == 0) {
                            targetX = i;
                            targetY = j + 1;
                        } else if (i + 1 <= 9 && matrixBoard[i + 1][j] == 0) {
                            targetX = i + 1;
                            targetY = j;
                        } else if (j - 1 >= 0 && matrixBoard[i][j - 1] == 0) {
                            targetX = i;
                            targetY = j - 1;
                        } else if (i - 1 >= 0 && matrixBoard[i - 1][j] == 0) {
                            targetX = i - 1;
                            targetY = j;
                        }
                        isTargetValid = true; // Set valid target as we found an adjacent empty cell
                    }

                    // Perform the attack on the found target
                    int attackMarkIndex = stageModel.getPlayerAttackMarkIndex(model.getIdPlayer());
                    String logText = stageModel.attack(model.getIdPlayer(), attackMarkIndex, targetX, targetY);
                    stageModel.getLog().setText(logText);
                    x = targetX;
                    y = targetY;
                    return; // Exit the method after performing the attack
                }
            }
        }
    }


    @Override
    public ActionList decide() {
        definePlayingMode();
        if (playingMode == 1) {
            attackHunt();
        } else if (playingMode == 2) {
            attackAttack();
        }
        return new ActionList();
    }
}
