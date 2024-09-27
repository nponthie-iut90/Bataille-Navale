package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;

/**
 * Class that create every single game element required for the game
 */
public class StageModelFactory extends StageElementsFactory {
    private final StageModel stageModel;

    /**
     * Required constructor by BoardFire
     *
     * @param gameStageModel the game stage model to be given
     */
    public StageModelFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (StageModel) gameStageModel;
    }

    /**
     * Create all game elements and add them to the stage model
     */
    @Override
    public void setup() {
        // create the different boards
        // create the player 1 board
        Board player1Board = new Board(0, 0, stageModel);
        stageModel.setPlayer1Board(player1Board);
        stageModel.hidePlayer1Board();
        // create the player 2 board
        Board player2Board = new Board(0, 0, stageModel);
        stageModel.setPlayer2Board(player2Board);
        stageModel.hidePlayer2Board();
        // create the player 1 attack board
        Board player1AttackBoard = new Board(100, 0, stageModel);
        stageModel.setPlayer1AttackBoard(player1AttackBoard);
        stageModel.hidePlayer1AttackBoard();
        // create the player 2 attack board
        Board player2AttackBoard = new Board(100, 0, stageModel);
        stageModel.setPlayer2AttackBoard(player2AttackBoard);
        stageModel.hideSecondAttackBoard();
        // create all the ships
        // game variant 1
        // player 1 ships
        Ship player1AircraftCarrierV1 = new Ship(stageModel, 5, "aircraft-carrier", 1);
        stageModel.getPlayer1ShipsV1().add(player1AircraftCarrierV1);
        Ship player1CruiserV1 = new Ship(stageModel, 4, "cruiser", 1);
        stageModel.getPlayer1ShipsV1().add(player1CruiserV1);
        Ship player1Destroyer1V1 = new Ship(stageModel, 3, "destroyer n°1", 1);
        stageModel.getPlayer1ShipsV1().add(player1Destroyer1V1);
        Ship player1Destroyer2V1 = new Ship(stageModel, 3, "destroyer n°2", 1);
        stageModel.getPlayer1ShipsV1().add(player1Destroyer2V1);
        Ship player1TorpedoBoatV1 = new Ship(stageModel, 2, "torpedo boat", 1);
        stageModel.getPlayer1ShipsV1().add(player1TorpedoBoatV1);
        // player 2 ships
        Ship player2AircraftCarrierV1 = new Ship(stageModel, 5, "aircraft-carrier", 2);
        stageModel.getPlayer2ShipsV1().add(player2AircraftCarrierV1);
        Ship player2CruiserV1 = new Ship(stageModel, 4, "cruiser", 2);
        stageModel.getPlayer2ShipsV1().add(player2CruiserV1);
        Ship player2Destroyer1V1 = new Ship(stageModel, 3, "destroyer n°1", 2);
        stageModel.getPlayer2ShipsV1().add(player2Destroyer1V1);
        Ship player2Destroyer2V1 = new Ship(stageModel, 3, "destroyer n°2", 2);
        stageModel.getPlayer2ShipsV1().add(player2Destroyer2V1);
        Ship player2TorpedoBoatV1 = new Ship(stageModel, 2, "torpedo boat", 2);
        stageModel.getPlayer2ShipsV1().add(player2TorpedoBoatV1);
        // game variant 2
        // player 1 ships
        Ship player1BattleShipV2 = new Ship(stageModel, 4, "cruiser", 1);
        stageModel.getPlayer1ShipsV2().add(player1BattleShipV2);
        Ship player1Cruiser1V2 = new Ship(stageModel, 3, "cruiser n°1", 1);
        stageModel.getPlayer1ShipsV2().add(player1Cruiser1V2);
        Ship player1Cruiser2V2 = new Ship(stageModel, 3, "cruiser n°2", 1);
        stageModel.getPlayer1ShipsV2().add(player1Cruiser2V2);
        Ship player1TorpedoBoat1V2 = new Ship(stageModel, 2, "torpedo boat n°1", 1);
        stageModel.getPlayer1ShipsV2().add(player1TorpedoBoat1V2);
        Ship player1TorpedoBoat2V2 = new Ship(stageModel, 2, "torpedo boat n°2", 1);
        stageModel.getPlayer1ShipsV2().add(player1TorpedoBoat2V2);
        Ship player1TorpedoBoat3V2 = new Ship(stageModel, 2, "torpedo boat n°3", 1);
        stageModel.getPlayer1ShipsV2().add(player1TorpedoBoat3V2);
        Ship player1Submarine1V2 = new Ship(stageModel, 1, "submarine n°1", 1);
        stageModel.getPlayer1ShipsV2().add(player1Submarine1V2);
        Ship player1Submarine2V2 = new Ship(stageModel, 1, "submarine n°2", 1);
        stageModel.getPlayer1ShipsV2().add(player1Submarine2V2);
        Ship player1Submarine3V2 = new Ship(stageModel, 1, "submarine n°3", 1);
        stageModel.getPlayer1ShipsV2().add(player1Submarine3V2);
        Ship player1Submarine4V2 = new Ship(stageModel, 1, "submarine n°4", 1);
        stageModel.getPlayer1ShipsV2().add(player1Submarine4V2);
        // player 2 ships
        Ship player2BattleShipV2 = new Ship(stageModel, 4, "cruiser", 2);
        stageModel.getPlayer2ShipsV2().add(player2BattleShipV2);
        Ship player2Cruiser1V2 = new Ship(stageModel, 3, "cruiser n°1", 2);
        stageModel.getPlayer2ShipsV2().add(player2Cruiser1V2);
        Ship player2Cruiser2V2 = new Ship(stageModel, 3, "cruiser n°2", 2);
        stageModel.getPlayer2ShipsV2().add(player2Cruiser2V2);
        Ship player2TorpedoBoat1V2 = new Ship(stageModel, 2, "torpedo boat n°1", 2);
        stageModel.getPlayer2ShipsV2().add(player2TorpedoBoat1V2);
        Ship player2TorpedoBoat2V2 = new Ship(stageModel, 2, "torpedo boat n°2", 2);
        stageModel.getPlayer2ShipsV2().add(player2TorpedoBoat2V2);
        Ship player2TorpedoBoat3V2 = new Ship(stageModel, 2, "torpedo boat n°3", 2);
        stageModel.getPlayer2ShipsV2().add(player2TorpedoBoat3V2);
        Ship player2Submarine1V2 = new Ship(stageModel, 1, "submarine n°1", 2);
        stageModel.getPlayer2ShipsV2().add(player2Submarine1V2);
        Ship player2Submarine2V2 = new Ship(stageModel, 1, "submarine n°2", 2);
        stageModel.getPlayer2ShipsV2().add(player2Submarine2V2);
        Ship player2Submarine3V2 = new Ship(stageModel, 1, "submarine n°3", 2);
        stageModel.getPlayer2ShipsV2().add(player2Submarine3V2);
        Ship player2Submarine4V2 = new Ship(stageModel, 1, "submarine n°4", 2);
        stageModel.getPlayer2ShipsV2().add(player2Submarine4V2);
        // create attack marks for every player
        // attack marks of the player 2
        for (int i = 0; i < 99; i++) {
            AttackMark attackMark = new AttackMark(stageModel, 1);
            stageModel.addElement(attackMark);
            stageModel.getPlayer1AttackMarks().add(attackMark);
        }
        // attack marks for the player 2
        for (int i = 0; i < 99; i++) {
            AttackMark attackMark = new AttackMark(stageModel, 1);
            stageModel.addElement(attackMark);
            stageModel.getPlayer2AttackMarks().add(attackMark);
        }
    }
}
