package control;

import boardifier.control.Controller;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudrickGPTToPlayTest {

    private AudrickGPTToPlay audrickGPTToPlay;
    private Model model;
    private Controller controller;
    private StageModel stageModel;
    private Board playerBoard;
    private Board opponentBoard;
    private Board playerAttackBoard;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        controller = mock(Controller.class);
        stageModel = mock(StageModel.class);
        playerBoard = mock(Board.class);
        opponentBoard = mock(Board.class);
        playerAttackBoard = mock(Board.class);
        audrickGPTToPlay = new AudrickGPTToPlay(model, controller);
        when(model.getGameStage()).thenReturn(stageModel);
        when(stageModel.defineOpposantBoard(anyInt())).thenReturn(opponentBoard);
        when(stageModel.defineAttackBoard(anyInt())).thenReturn(playerAttackBoard);
    }

    @Test
    void testGenerateRandomNumber() {
        AudrickGPTToPlay audrickGPTToPlayWithMockedRandom = spy(audrickGPTToPlay);
        doReturn(5).when(audrickGPTToPlayWithMockedRandom).generateRandomNumber();
        assertEquals(5, audrickGPTToPlayWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testAttackHunt() {
        audrickGPTToPlay.x = -1;
        audrickGPTToPlay.y = -1;
        audrickGPTToPlay.attackHunt();
        assertTrue(audrickGPTToPlay.x >= 0 && audrickGPTToPlay.x < 10);
        assertTrue(audrickGPTToPlay.y >= 0 && audrickGPTToPlay.y < 10);
    }

    @Test
    void testDefinePlayingMode_random() {
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(null);
        audrickGPTToPlay.definePlayingMode();
        assertEquals(1, audrickGPTToPlay.playingMode);
    }

    @Test
    void testDefinePlayingMode_attack() {
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        audrickGPTToPlay.definePlayingMode();
        assertEquals(2, audrickGPTToPlay.playingMode);
    }

    @Test
    void testCasesAlreadyHit() {
        when(playerAttackBoard.getElement(anyInt(), anyInt())).thenReturn(mock(AttackMark.class));
        audrickGPTToPlay.casesAlreadyHit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(-1, audrickGPTToPlay.matrixBoard[i][j]);
            }
        }
    }

    @Test
    void testShipsAlreadyHit() {
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(true);
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        audrickGPTToPlay.shipsAlreadyHit();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(-1, audrickGPTToPlay.matrixBoard[i][j],
                        "matrixBoard[" + i + "][" + j + "] should be -1");
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
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        assertFalse(audrickGPTToPlay.isShipHurt());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, audrickGPTToPlay.matrixBoard[i][j]);
            }
        }
    }

    /* boardifier error
    @Test
    void testAttackAttack() {
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        audrickGPTToPlay.attackAttack();
        assertTrue(audrickGPTToPlay.x >= 0 && audrickGPTToPlay.x < 10);
        assertTrue(audrickGPTToPlay.y >= 0 && audrickGPTToPlay.y < 10);
    }

    @Test
    void testDecide() {
        AudrickGPTToPlay spyAudrickGPTToPlay = spy(audrickGPTToPlay);
        doReturn(5, 8).when(spyAudrickGPTToPlay).generateRandomNumber();

        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(shipPart.isDestroyed()).thenReturn(true);
        when(ship.isShipDestroyed()).thenReturn(false);
        when(opponentBoard.getElement(anyInt(), anyInt())).thenReturn(shipPart);

        ActionList actionList = spyAudrickGPTToPlay.decide();
        assertNotNull(actionList);

        verify(stageModel, times(1)).attack(anyInt(), anyInt(), anyInt(), anyInt());
    }
     */
}
