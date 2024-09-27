package control;

import boardifier.model.Model;
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
        when(stageModel.defineOpposantBoard(anyInt())).thenReturn(player2Board);
        when(stageModel.defineAttackBoard(anyInt())).thenReturn(player1AttackBoard);
    }

    @Test
    void testGenerateRandomNumber() {
        CaiToPlay caiToPlayWithMockedRandom = spy(caiToPlay);
        doReturn(5).when(caiToPlayWithMockedRandom).generateRandomNumber();
        assertEquals(5, caiToPlayWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testAttackHunt() {
        when(model.getIdPlayer()).thenReturn(1);
        when(stageModel.getPlayerAttackMarkIndex(anyInt())).thenReturn(1);
        when(stageModel.isPlayerHasAlreadyShotOnThisCase(anyInt(), anyInt(), anyInt())).thenReturn(false);
        caiToPlay.attackHunt();
        assertTrue(caiToPlay.x >= 0 && caiToPlay.x < 10);
        assertTrue(caiToPlay.y >= 0 && caiToPlay.y < 10);
    }

    @Test
    void testDefinePlayingMode_random() {
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(null);
        caiToPlay.definePlayingMode();
        assertEquals(1, caiToPlay.playingMode);
    }

    @Test
    void testDefinePlayingMode_attack() {
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
        when(model.getIdPlayer()).thenReturn(1);
        when(player1AttackBoard.getElement(anyInt(), anyInt())).thenReturn(mock(AttackMark.class));
        caiToPlay.casesAlreadyHit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(-1, caiToPlay.matrixBoard[i][j]);
            }
        }
    }

    @Test
    void testIsShipHurt() {
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        assertFalse(caiToPlay.isShipHurt());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player2Board.getElement(i, j) instanceof ShipPart) {
                    assertEquals(1, caiToPlay.matrixBoard[i][j]);
                }
            }
        }
    }

    /* boardifier error
    @Test
    void testAttackAttack() {
        when(model.getIdPlayer()).thenReturn(1);
        when(stageModel.getPlayerAttackMarkIndex(anyInt())).thenReturn(1);
        caiToPlay.casesAlreadyHit();
        caiToPlay.shipsAlreadyHit();
        caiToPlay.isShipHurt();
        caiToPlay.attackAttack();
        verify(stageModel, times(1)).attack(anyInt(), anyInt(), anyInt(), anyInt());
    }
     */

    @Test
    void testDecide() {
        CaiToPlay spyCaiToPlay = spy(caiToPlay);
        doNothing().when(spyCaiToPlay).attackHunt();
        doNothing().when(spyCaiToPlay).attackAttack();
        when(model.getIdPlayer()).thenReturn(1);
        spyCaiToPlay.decide();
        verify(spyCaiToPlay, atLeastOnce()).definePlayingMode();
        verify(spyCaiToPlay, atLeastOnce()).attackHunt();
    }
}