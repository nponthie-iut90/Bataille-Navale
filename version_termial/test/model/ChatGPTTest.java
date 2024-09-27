package control;

import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.StageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChatGPTTest {

    private ChatGPT chatGPT;
    private Model model;
    private GameController gameController;
    private StageModel stageModel;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        gameController = mock(GameController.class);
        stageModel = mock(StageModel.class);
        when(model.getGameStage()).thenReturn(stageModel);
        chatGPT = new ChatGPT(model, gameController);
    }

    @Test
    void testDefineCurrentPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        chatGPT.defineCurrentPlayer();
        assertEquals(1, chatGPT.player);
    }

    @Test
    void testDefineCurrentPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        chatGPT.defineCurrentPlayer();
        assertEquals(2, chatGPT.player);
    }

    @Test
    void testGenerateRandomLetterHV() {
        ChatGPT chatGPTWithMockedRandom = spy(chatGPT);
        doReturn(false).when(chatGPTWithMockedRandom).generateRandomLetterHV();
        assertFalse(chatGPTWithMockedRandom.generateRandomLetterHV());

        doReturn(true).when(chatGPTWithMockedRandom).generateRandomLetterHV();
        assertTrue(chatGPTWithMockedRandom.generateRandomLetterHV());
    }

    @Test
    void testGenerateRandomNumber() {
        ChatGPT chatGPTWithMockedRandom = spy(chatGPT);
        doReturn(5).when(chatGPTWithMockedRandom).generateRandomNumber();
        assertEquals(5, chatGPTWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testDecide() {
        when(stageModel.isShipWillBeInBoardAndLegal(anyBoolean(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false, true);
        ChatGPT spyChatGPT = spy(chatGPT);
        doReturn(5, 8).when(spyChatGPT).generateRandomNumber();
        doReturn(true).when(spyChatGPT).generateRandomLetterHV();
        ActionList actionList = spyChatGPT.decide();
        assertNotNull(actionList);
        verify(stageModel, times(1)).isShipWillBeInBoardAndLegal(true, 5, 8, 0,2,1);
    }
}
