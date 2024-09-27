package control;

import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.Ship;
import model.StageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CaiTest {

    private Cai cai;
    private Model model;
    private GameController gameController;
    private StageModel stageModel;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        gameController = mock(GameController.class);
        stageModel = mock(StageModel.class);
        when(model.getGameStage()).thenReturn(stageModel);
        cai = new Cai(model, gameController);
    }

    @Test
    void testDefineCurrentPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        assertEquals(1, cai.defineCurrentPlayer());
    }

    @Test
    void testDefineCurrentPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        assertEquals(2, cai.defineCurrentPlayer());
    }

    @Test
    void testGenerateRandomLetterHV() {
        Cai caiWithMockedRandom = spy(cai);
        doReturn(true).when(caiWithMockedRandom).generateRandomLetterHV();
        assertTrue(caiWithMockedRandom.generateRandomLetterHV());

        doReturn(false).when(caiWithMockedRandom).generateRandomLetterHV();
        assertFalse(caiWithMockedRandom.generateRandomLetterHV());
    }

    @Test
    void testGenerateRandomLetter() {
        Cai caiWithMockedRandom = spy(cai);
        doReturn(0).when(caiWithMockedRandom).generateRandomNumber();
        assertEquals('A', caiWithMockedRandom.generateRandomLetter());

        doReturn(9).when(caiWithMockedRandom).generateRandomNumber();
        assertEquals('J', caiWithMockedRandom.generateRandomLetter());
    }

    @Test
    void testGenerateRandomNumber() {
        Cai caiWithMockedRandom = spy(cai);
        doReturn(5).when(caiWithMockedRandom).generateRandomNumber();
        assertEquals(5, caiWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testGenerateLine() {
        Cai caiWithMockedRandom = spy(cai);
        doReturn(true).when(caiWithMockedRandom).generateRandomLetterHV();
        doReturn(0).when(caiWithMockedRandom).generateRandomNumber();
        assertEquals("trueA0", caiWithMockedRandom.generateLine());

        doReturn(false).when(caiWithMockedRandom).generateRandomLetterHV();
        doReturn(9).when(caiWithMockedRandom).generateRandomNumber();
        assertEquals("falseJ9", caiWithMockedRandom.generateLine());
    }

    @Test
    void testDecide() {
        when(stageModel.isShipWillBeInBoardAndLegal(anyBoolean(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false, true);
        gameController.botShipIndex = 0;
        Ship ship = mock(Ship.class);
        List<Ship> ships = new ArrayList<>();
        ships.add(ship);
        when(stageModel.getPlayer1ShipsV1()).thenReturn((ArrayList<Ship>) ships);
        Cai spyCai = spy(cai);
        doReturn(5, 8).when(spyCai).generateRandomNumber();
        doReturn(true).when(spyCai).generateRandomLetterHV();
        ActionList actionList = spyCai.decide();
        assertNotNull(actionList);
        verify(stageModel, times(1)).isShipWillBeInBoardAndLegal(true, 5, 8, 0, 1, 1);
    }
}
