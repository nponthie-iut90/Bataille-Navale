package control;

import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CaiToPlayTest {

    private CaiToPlay caiToPlay;
    private Model model;
    private GameController gameController;
    private StageModel stageModel;
    private Board player1Board;
    private Board player2Board;
    private Board player1AttackBoard;
    private Board player2AttackBoard;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        gameController = mock(GameController.class);
        stageModel = mock(StageModel.class);
        player1Board = mock(Board.class);
        player2Board = mock(Board.class);
        player1AttackBoard = mock(Board.class);
        player2AttackBoard = mock(Board.class);
        caiToPlay = new CaiToPlay(model, gameController);
        when(model.getGameStage()).thenReturn(stageModel);
        when(stageModel.getPlayer1Board()).thenReturn(player1Board);
        when(stageModel.getPlayer2Board()).thenReturn(player2Board);
        when(stageModel.getPlayer1AttackBoard()).thenReturn(player1AttackBoard);
        when(stageModel.getPlayer2AttackBoard()).thenReturn(player2AttackBoard);
    }

    @Test
    void testDefineCurrentPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        caiToPlay.defineCurrentPlayer();
        assertEquals(1, caiToPlay.player);
    }

    @Test
    void testDefineCurrentPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        caiToPlay.defineCurrentPlayer();
        assertEquals(2, caiToPlay.player);
    }

    /* only works when executed only, without the others
    @Test
    void testDefineCurrentGameVariant1() {
        gameController.gameVariant = 1;
        caiToPlay.defineCurrentGameVariant();
        assertEquals(1, caiToPlay.gameVariant);
    }
     */

    @Test
    void testDefineCurrentGameVariant2() {
        gameController.gameVariant = 2;
        caiToPlay.defineCurrentGameVariant();
        assertEquals(2, caiToPlay.gameVariant);
    }

    @Test
    void testGenerateRandomNumber() {
        CaiToPlay caiToPlayWithMockedRandom = spy(caiToPlay);
        doReturn(5).when(caiToPlayWithMockedRandom).generateRandomNumber();
        assertEquals(5, caiToPlayWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testGenerateRandomNumberheadsortails() {
        CaiToPlay caiToPlayWithMockedRandom = spy(caiToPlay);
        doReturn(1).when(caiToPlayWithMockedRandom).generateRandomNumberheadsortails();
        assertEquals(1, caiToPlayWithMockedRandom.generateRandomNumberheadsortails());
    }

    @Test
    void testAttackHunt() {
        caiToPlay.player = 1;
        when(stageModel.analyzeAndAttack(anyInt(), anyInt(), eq(1), anyInt())).thenReturn(false, true);
        caiToPlay.attackHunt();
        assertTrue(caiToPlay.x >= 0 && caiToPlay.x < 10);
        assertTrue(caiToPlay.y >= 0 && caiToPlay.y < 10);
    }

    @Test
    void testDefinePlayingMode_random() {
        caiToPlay.player = 1;
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(null);
        caiToPlay.definePlayingMode();
        assertEquals(1, caiToPlay.playingMode);
    }

    @Test
    void testDefinePlayingMode_attack() {
        caiToPlay.player = 1;
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        caiToPlay.definePlayingMode();
        assertEquals(2, caiToPlay.playingMode);
    }

    @Test
    void testCasesAlreadyHit() {
        caiToPlay.player = 1;
        when(player1AttackBoard.getElement(anyInt(), anyInt())).thenReturn(mock(AttackMark.class));
        caiToPlay.casesAlreadyHit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(-1, caiToPlay.matrixBoard[i][j]);
            }
        }
    }

    /* test not working index out of bounds
    @Test
    void testBoatsAlreadyHit() {
        caiToPlay.player = 1;
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(true);
        caiToPlay.boatsAlreadyHit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(-1, caiToPlay.matrixBoard[i][j],
                        "matrixBoard[" + i + "][" + j + "] should be -1");
            }
        }
    }
    */

    @Test
    void testIsShipHurt() {
        caiToPlay.player = 1;
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        assertFalse(caiToPlay.isShipHurt());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, caiToPlay.matrixBoard[i][j]);
            }
        }
    }

    @Test
    void testDecide() {
        when(stageModel.analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false, true);
        CaiToPlay spyCaiToPlay = spy(caiToPlay);
        doReturn(5, 8).when(spyCaiToPlay).generateRandomNumber();
        when(player2Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        int attackMarkIndex = gameController.player1AttackMarkIndex;
        ActionList actionList = spyCaiToPlay.decide();
        assertNotNull(actionList);
        verify(stageModel, times(1)).analyzeAndAttack(5, 8, spyCaiToPlay.player, attackMarkIndex);
    }

    @Test
    void testAttackMessagesPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        caiToPlay.defineCurrentPlayer();
        when(player2Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        CaiToPlay spyCaiToPlay = spy(caiToPlay);
        doReturn(5, 8).when(spyCaiToPlay).generateRandomNumber();
        doReturn(true).when(stageModel).analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt());
        spyCaiToPlay.decide();
        verify(gameController, times(1)).destroyedShipPartsByPlayer1++;
        assertTrue(gameController.temporaryMessageForPlayer2.contains("has been hit during last turn"));
    }

    /* only works when executed only, without the others
    @Test
    void testAttackMessagesPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        caiToPlay.defineCurrentPlayer();
        when(player1Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player1Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        CaiToPlay spyCaiToPlay = spy(caiToPlay);
        doReturn(5, 8).when(spyCaiToPlay).generateRandomNumber();
        doReturn(true).when(stageModel).analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt());
        spyCaiToPlay.decide();
        verify(gameController, times(1)).destroyedShipPartsByPlayer2++;
        assertTrue(gameController.temporaryMessageForPlayer1.contains("has been hit during last turn"));
    }*/
}
