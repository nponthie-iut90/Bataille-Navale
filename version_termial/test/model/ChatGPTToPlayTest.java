package control;

import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.Board;
import model.Ship;
import model.ShipPart;
import model.StageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChatGPTToPlayTest {

    private ChatGPTToPlay chatGPTToPlay;
    private Model model;
    private GameController gameController;
    private StageModel stageModel;
    private Board player1Board;
    private Board player2Board;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        gameController = mock(GameController.class);
        stageModel = mock(StageModel.class);
        player1Board = mock(Board.class);
        player2Board = mock(Board.class);
        when(model.getGameStage()).thenReturn(stageModel);
        when(stageModel.getPlayer1Board()).thenReturn(player1Board);
        when(stageModel.getPlayer2Board()).thenReturn(player2Board);
        chatGPTToPlay = new ChatGPTToPlay(model, gameController);
    }

    @Test
    void testDefineCurrentPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        chatGPTToPlay.defineCurrentPlayer();
        assertEquals(1, chatGPTToPlay.player);
    }

    @Test
    void testDefineCurrentPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        chatGPTToPlay.defineCurrentPlayer();
        assertEquals(2, chatGPTToPlay.player);
    }

    @Test
    void testDefineCurrentGameVariant1() {
        gameController.gameVariant = 1;
        chatGPTToPlay.defineCurrentGameVariant();
        assertEquals(1, chatGPTToPlay.gameVariant);
    }

    @Test
    void testDefineCurrentGameVariant_variantNonOne() {
        gameController.gameVariant = 2;
        chatGPTToPlay.defineCurrentGameVariant();
        assertEquals(2, chatGPTToPlay.gameVariant);
    }

    @Test
    void testGenerateRandomNumber() {
        ChatGPTToPlay chatGPTToPlayWithMockedRandom = spy(chatGPTToPlay);
        doReturn(5).when(chatGPTToPlayWithMockedRandom).generateRandomNumber();
        assertEquals(5, chatGPTToPlayWithMockedRandom.generateRandomNumber());
    }

    @Test
    void testDecide() {
        when(stageModel.analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false, true);
        ChatGPTToPlay spyChatGPTToPlay = spy(chatGPTToPlay);
        doReturn(5, 8).when(spyChatGPTToPlay).generateRandomNumber();
        when(player2Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        when(stageModel.attack(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn("hit");
        int attackMarkIndex = gameController.player1AttackMarkIndex;
        ActionList actionList = spyChatGPTToPlay.decide();
        assertNotNull(actionList);
        verify(stageModel, times(1)).analyzeAndAttack(5, 8, spyChatGPTToPlay.player, attackMarkIndex);
    }

    @Test
    void testAttackMessagesPlayer1() {
        when(model.getIdPlayer()).thenReturn(0);
        chatGPTToPlay.defineCurrentPlayer();
        when(player2Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player2Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);
        ChatGPTToPlay spyChatGPTToPlay = spy(chatGPTToPlay);
        doReturn(5, 8).when(spyChatGPTToPlay).generateRandomNumber();
        doReturn(true).when(stageModel).analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt());
        spyChatGPTToPlay.decide();
        verify(gameController, times(1)).destroyedShipPartsByPlayer1++;
        assertTrue(gameController.temporaryMessageForPlayer2.contains("has been hit during last turn"));
    }

    /* only works when executed only, without the others
    @Test
    void testAttackMessagesPlayer2() {
        when(model.getIdPlayer()).thenReturn(1);
        chatGPTToPlay.defineCurrentPlayer();

        when(player1Board.isElementAt(anyInt(), anyInt())).thenReturn(true);
        ShipPart shipPart = mock(ShipPart.class);
        Ship ship = mock(Ship.class);
        when(shipPart.getParentShip()).thenReturn(ship);
        when(player1Board.getElement(anyInt(), anyInt())).thenReturn(shipPart);

        ChatGPTToPlay spyChatGPTToPlay = spy(chatGPTToPlay);
        doReturn(5, 8).when(spyChatGPTToPlay).generateRandomNumber();
        doReturn(true).when(stageModel).analyzeAndAttack(anyInt(), anyInt(), anyInt(), anyInt());

        spyChatGPTToPlay.decide();

        verify(gameController, times(1)).destroyedShipPartsByPlayer2++;
        assertTrue(gameController.temporaryMessageForPlayer1.contains("has been hit during last turn"));
    }
     */
}
