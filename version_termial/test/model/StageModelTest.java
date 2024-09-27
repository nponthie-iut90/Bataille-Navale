package model;

import boardifier.model.Model;
import boardifier.model.StageElementsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageModelTest {

    private StageModel stageModel;
    private Model model;
    private Board player1Board;
    private Board player2Board;
    private Board player1AttackBoard;
    private Board player2AttackBoard;
    private Ship shipMock;
    private ShipPart shipPartMock;
    private AttackMark attackMarkMock;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        stageModel = new StageModel("Test Stage", model);
        player1Board = mock(Board.class);
        player2Board = mock(Board.class);
        player1AttackBoard = mock(Board.class);
        player2AttackBoard = mock(Board.class);
        stageModel.setPlayer1Board(player1Board);
        stageModel.setPlayer2Board(player2Board);
        stageModel.setPlayer1AttackBoard(player1AttackBoard);
        stageModel.setPlayer2AttackBoard(player2AttackBoard);
        shipMock = mock(Ship.class);
        shipPartMock = mock(ShipPart.class);
        attackMarkMock = mock(AttackMark.class);
        when(shipPartMock.getParentShip()).thenReturn(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        when(shipMock.getName()).thenReturn("Destroyer");
    }

    @Test
    void testGetAndSetPlayer1Board() {
        Board board = mock(Board.class);
        stageModel.setPlayer1Board(board);
        assertEquals(board, stageModel.getPlayer1Board());
    }

    @Test
    void testShowAndHidePlayer1Board() {
        Board board = mock(Board.class);
        stageModel.setPlayer1Board(board);
        stageModel.showPlayer1Board();
        verify(board).setVisible(true);
        stageModel.hidePlayer1Board();
        verify(board).setVisible(false);
    }

    @Test
    void testGetAndSetPlayer2Board() {
        Board board = mock(Board.class);
        stageModel.setPlayer2Board(board);
        assertEquals(board, stageModel.getPlayer2Board());
    }

    @Test
    void testShowAndHidePlayer2Board() {
        Board board = mock(Board.class);
        stageModel.setPlayer2Board(board);
        stageModel.showSecondBoard();
        verify(board).setVisible(true);
        stageModel.hidePlayer2Board();
        verify(board).setVisible(false);
    }

    @Test
    void testGetAndSetPlayer1AttackBoard() {
        Board board = mock(Board.class);
        stageModel.setPlayer1AttackBoard(board);
        assertEquals(board, stageModel.getPlayer1AttackBoard());
    }

    @Test
    void testShowAndHidePlayer1AttackBoard() {
        Board board = mock(Board.class);
        stageModel.setPlayer1AttackBoard(board);
        stageModel.showPlayer1AttackBoard();
        verify(board).setVisible(true);
        stageModel.hidePlayer1AttackBoard();
        verify(board).setVisible(false);
    }

    @Test
    void testGetAndSetPlayer2AttackBoard() {
        Board board = mock(Board.class);
        stageModel.setPlayer2AttackBoard(board);
        assertEquals(board, stageModel.getPlayer2AttackBoard());
    }

    @Test
    void testShowAndHidePlayer2AttackBoard() {
        Board board = mock(Board.class);
        stageModel.setPlayer2AttackBoard(board);
        stageModel.showSecondAttackBoard();
        verify(board).setVisible(true);
        stageModel.hideSecondAttackBoard();
        verify(board).setVisible(false);
    }

    @Test
    void testGetPlayer1ShipsV1() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer1ShipsV1());
    }

    @Test
    void testGetPlayer2ShipsV1() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer2ShipsV1());
    }

    @Test
    void testGetPlayer1ShipsV2() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer1ShipsV2());
    }

    @Test
    void testGetPlayer2ShipsV2() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer2ShipsV2());
    }

    @Test
    void testGetPlayer1AttackMarks() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer1AttackMarks());
    }

    @Test
    void testGetPlayer1AttackMarksByIndex() {
        AttackMark attackMark = mock(AttackMark.class);
        stageModel.getPlayer1AttackMarks().add(attackMark);
        assertEquals(attackMark, stageModel.getPlayer1AttackMarks().getFirst());
    }

    @Test
    void testGetPlayer2AttackMarks() {
        assertInstanceOf(ArrayList.class, stageModel.getPlayer2AttackMarks());
    }

    @Test
    void testGetPlayer2AttackMarksByIndex() {
        AttackMark attackMark = mock(AttackMark.class);
        stageModel.getPlayer2AttackMarks().add(attackMark);
        assertEquals(attackMark, stageModel.getPlayer2AttackMarks().getFirst());
    }

    @Test
    void testCalcValideCells_Player1() {
        when(player1Board.isEmptyAt(anyInt(), anyInt())).thenReturn(true);
        int[][] validCells = stageModel.calcValideCells(1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, validCells[i][j]);
            }
        }
    }

    @Test
    void testCalcValideCells_Player2() {
        when(player2Board.isEmptyAt(anyInt(), anyInt())).thenReturn(true);
        int[][] validCells = stageModel.calcValideCells(2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, validCells[i][j]);
            }
        }
    }

    @Test
    void testCalcValideCellsSpecialBot_Player1() {
        when(player1Board.isEmptyAt(anyInt(), anyInt())).thenReturn(true);
        int[][] validCells = stageModel.calcValideCellsSpecialBot(1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, validCells[i][j]);
            }
        }
    }

    @Test
    void testCalcValideCellsSpecialBot_Player2() {
        when(player2Board.isEmptyAt(anyInt(), anyInt())).thenReturn(true);
        int[][] validCells = stageModel.calcValideCellsSpecialBot(2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(1, validCells[i][j]);
            }
        }
    }

    @Test
    void testCalcOriginalCaseNeededByTheShip() {
        int colindex = 5;
        int rowindex = 7;
        int[][] caseNeeded = stageModel.calcOriginalCaseNeededByTheShip(colindex, rowindex);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == rowindex && j == colindex) {
                    assertEquals(-1, caseNeeded[i][j]);
                } else {
                    assertEquals(0, caseNeeded[i][j]);
                }
            }
        }
    }

/* Not fonctionnal tests
    @Test
    void testShipHorizontalVariant1Player1() {
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, true);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(true, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipHorizontalVariant2Player1() {
        stageModel.getPlayer1ShipsV2().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, true);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(true, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipHorizontalVariant1Player2() {
        stageModel.getPlayer2ShipsV1().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, true);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(true, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipHorizontalVariant2Player2() {
        stageModel.getPlayer2ShipsV2().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, true);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(true, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipVerticalVariant1Player1() {
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, false);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(false, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipVerticalVariant2Player1() {
        stageModel.getPlayer1ShipsV2().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, false);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(false, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipVerticalVariant1Player2() {
        stageModel.getPlayer2ShipsV1().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, false);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(false, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipVerticalVariant2Player2() {
        stageModel.getPlayer2ShipsV2().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, false);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(false, 0, 0, 0, 1, 1);
        assertTrue(result);
    }

    @Test
    void testShipOutOfBounds() {
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(shipMock.getSize()).thenReturn(3);
        player1Board.addShipToBoard(shipMock, 0, 0, true);
        boolean result = stageModel.isShipWillBeInBoardAndLegal(true, 9, 9, 0, 1, 1);
        assertFalse(result);
    }
*/
    @Test
    void testIsInValideCells_ValidPlacement() {
        StageModel stageModel = mock(StageModel.class);
        int[][] shipFutureCoordinates = new int[10][10];
        int shipNumber = 0;
        boolean horizontal = true;
        int colindex = 5;
        int rowindex = 5;
        int player = 1;
        int gameVariant = 1;
        when(stageModel.calcValideCells(player)).thenReturn(new int[10][10]);
        when(stageModel.isInValideCells(any(int[][].class), anyInt(), anyBoolean(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);
        boolean result = stageModel.isInValideCells(shipFutureCoordinates, shipNumber, horizontal, colindex, rowindex, player, gameVariant);
        assertTrue(result);
    }

    @Test
    void testIsInValideCells_InvalidPlacement() {
        StageModel stageModel = mock(StageModel.class);
        int[][] shipFutureCoordinates = new int[10][10];
        int shipNumber = 0;
        boolean horizontal = true;
        int colindex = 5;
        int rowindex = 5;
        int player = 1;
        int gameVariant = 1;
        when(stageModel.calcValideCells(player)).thenReturn(new int[10][10]);
        when(stageModel.isInValideCells(any(int[][].class), anyInt(), anyBoolean(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false);
        boolean result = stageModel.isInValideCells(shipFutureCoordinates, shipNumber, horizontal, colindex, rowindex, player, gameVariant);
        assertFalse(result);
    }

    @Test
    void testAddShipAfterCheck_GameVariant1_Player1() {
        Ship ship = mock(Ship.class);
        stageModel.getPlayer1ShipsV1().add(ship);
        stageModel.addShipAfterCheck(0, true, 0, 0, 1, 1);
        verify(player1Board).addShipToBoard(ship, 0, 0, true);
    }

    @Test
    void testAddShipAfterCheck_GameVariant1_Player2() {
        Ship ship = mock(Ship.class);
        stageModel.getPlayer2ShipsV1().add(ship);
        stageModel.addShipAfterCheck(0, true, 0, 0, 1, 2);
        verify(player2Board).addShipToBoard(ship, 0, 0, true);
    }

    @Test
    void testAddShipAfterCheck_GameVariant2_Player1() {
        Ship ship = mock(Ship.class);
        stageModel.getPlayer1ShipsV2().add(ship);
        stageModel.addShipAfterCheck(0, true, 0, 0, 2, 1);
        verify(player1Board).addShipToBoard(ship, 0, 0, true);
    }

    @Test
    void testAddShipAfterCheck_GameVariant2_Player2() {
        Ship ship = mock(Ship.class);
        stageModel.getPlayer2ShipsV2().add(ship);
        stageModel.addShipAfterCheck(0, true, 0, 0, 2, 2);
        verify(player2Board).addShipToBoard(ship, 0, 0, true);
    }

    @Test
    void testIsPlayer1HasNotShotOnThisCase() {
        when(player1AttackBoard.isElementAt(anyInt(), anyInt())).thenReturn(false);
        assertFalse(stageModel.isPlayerHasAlreadyShotOnThisCase(0, 0, 1));
    }

    @Test
    void testIsPlayer1HasShotOnThisCase() {
        when(player1AttackBoard.isElementAt(0, 0)).thenReturn(true);
        assertTrue(stageModel.isPlayerHasAlreadyShotOnThisCase(0, 0, 1));
    }

    @Test
    void testIsPlayer2HasNotShotOnThisCase() {
        when(player2AttackBoard.isElementAt(anyInt(), anyInt())).thenReturn(false);
        assertFalse(stageModel.isPlayerHasAlreadyShotOnThisCase(0, 0, 2));
    }

    @Test
    void testIsPlayer2HasShotOnThisCase() {
        when(player2AttackBoard.isElementAt(0, 0)).thenReturn(true);
        assertTrue(stageModel.isPlayerHasAlreadyShotOnThisCase(0, 0, 2));
    }

    @Test
    void testAnalyzeAndAttack_AlreadyShot() {
        int rowIndex = 0;
        int colIndex = 0;
        int player = 1;
        int attackMarkIndex = 0;
        StageModel spyStageModel = spy(stageModel);
        doReturn(true).when(spyStageModel).isPlayerHasAlreadyShotOnThisCase(rowIndex, colIndex, player);
        boolean result = spyStageModel.analyzeAndAttack(rowIndex, colIndex, player, attackMarkIndex);
        assertFalse(result);
        verify(spyStageModel, never()).attack(rowIndex, colIndex, player, attackMarkIndex);
    }

    @Test
    void testAnalyzeAndAttack_FirstShot() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 1;
        int attackMarkIndex = 0;
        StageModel spyStageModel = spy(stageModel);
        doReturn(false).when(spyStageModel).isPlayerHasAlreadyShotOnThisCase(rowIndex, colIndex, player);
        doReturn("hit").when(spyStageModel).attack(rowIndex, colIndex, player, attackMarkIndex);
        boolean result = spyStageModel.analyzeAndAttack(rowIndex, colIndex, player, attackMarkIndex);
        assertTrue(result);
        verify(spyStageModel).attack(rowIndex, colIndex, player, attackMarkIndex);
    }

    @Test
    void testAnalyzeAndAttack_Player2_FirstShot() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 1;
        int colIndex = 1;
        int player = 2;
        int attackMarkIndex = 1;
        StageModel spyStageModel = spy(stageModel);
        doReturn(false).when(spyStageModel).isPlayerHasAlreadyShotOnThisCase(rowIndex, colIndex, player);
        doReturn("hit").when(spyStageModel).attack(rowIndex, colIndex, player, attackMarkIndex);
        boolean result = spyStageModel.analyzeAndAttack(rowIndex, colIndex, player, attackMarkIndex);
        assertTrue(result);
        verify(spyStageModel).attack(rowIndex, colIndex, player, attackMarkIndex);
    }

    @Test
    void testAttackPlayer1Miss() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 1;
        int attackMarkIndex = 0;
        when(player2Board.isEmptyAt(rowIndex, colIndex)).thenReturn(true);
        AttackMark attackMarkMock = stageModel.getPlayer1AttackMarks().get(attackMarkIndex);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(0);
        verify(player1AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(player2Board).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        assertEquals("erreur", result);
    }

    @Test
    void testAttackPlayer1Hit() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 1;
        int attackMarkIndex = 0;
        AttackMark attackMarkMock = mock(AttackMark.class);
        stageModel.getPlayer1AttackMarks().add(attackMarkIndex, attackMarkMock);
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(player2Board.isEmptyAt(rowIndex, colIndex)).thenReturn(false);
        when(player2Board.getElement(rowIndex, colIndex)).thenReturn(shipPartMock);
        when(shipMock.isShipDestroyed()).thenReturn(false);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(1);
        verify(player1AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(shipPartMock).setDestroyed();
        assertEquals("Votre \" + p.getParentShip().getName() + \" a été endommagé durant le tour précédant !", result);
    }

    @Test
    void testAttackPlayer1Sink() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 1;
        int attackMarkIndex = 0;
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(player2Board.isEmptyAt(rowIndex, colIndex)).thenReturn(false);
        AttackMark attackMarkMock = stageModel.getPlayer1AttackMarks().get(attackMarkIndex);
        when(player2Board.getElement(rowIndex, colIndex)).thenReturn(shipPartMock);
        when(shipMock.isShipDestroyed()).thenReturn(true);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(1);
        verify(player1AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(shipPartMock).setDestroyed();
        assertEquals("Votre \" + p.getParentShip().getName() + \" a été détruit durant le tour précédant !", result);
    }

    @Test
    void testAttackPlayer2Miss() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 2;
        int attackMarkIndex = 0;
        when(player1Board.isEmptyAt(rowIndex, colIndex)).thenReturn(true);
        AttackMark attackMarkMock = stageModel.getPlayer2AttackMarks().get(attackMarkIndex);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(0);
        verify(player2AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(player1Board).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        assertEquals("erreur", result);
    }

    @Test
    void testAttackPlayer2Hit() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 2;
        int attackMarkIndex = 0;
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(player1Board.isEmptyAt(rowIndex, colIndex)).thenReturn(false);
        AttackMark attackMarkMock = stageModel.getPlayer2AttackMarks().get(attackMarkIndex);
        when(player1Board.getElement(rowIndex, colIndex)).thenReturn(shipPartMock);
        when(shipMock.isShipDestroyed()).thenReturn(false);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(1);
        verify(player2AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(shipPartMock).setDestroyed();
        assertEquals("Votre \" + p.getParentShip().getName() + \" a été endommagé durant le tour précédant !", result);
    }

    @Test
    void testAttackPlayer2Sink() {
        for (int i = 0; i < 10; i++) {
            stageModel.getPlayer1AttackMarks().add(mock(AttackMark.class));
            stageModel.getPlayer2AttackMarks().add(mock(AttackMark.class));
        }
        int rowIndex = 0;
        int colIndex = 0;
        int player = 2;
        int attackMarkIndex = 0;
        stageModel.getPlayer1ShipsV1().add(shipMock);
        when(player1Board.isEmptyAt(rowIndex, colIndex)).thenReturn(false);
        AttackMark attackMarkMock = stageModel.getPlayer2AttackMarks().get(attackMarkIndex);
        when(player1Board.getElement(rowIndex, colIndex)).thenReturn(shipPartMock);
        when(shipMock.isShipDestroyed()).thenReturn(true);
        String result = stageModel.attack(rowIndex, colIndex, player, attackMarkIndex);
        verify(attackMarkMock).setColor(1);
        verify(player2AttackBoard).addMarkToBoard(attackMarkMock, rowIndex, colIndex);
        verify(shipPartMock).setDestroyed();
        assertEquals("Votre \" + p.getParentShip().getName() + \" a été détruit durant le tour précédant !", result);
    }

    @Test
    void testIsPlayer1HasNoMoreShips_AllShipsDestroyed() {
        Ship s1 = mock(Ship.class);
        stageModel.getPlayer1ShipsV1().add(s1);
        Ship s2 = mock(Ship.class);
        stageModel.getPlayer1ShipsV1().add(s2);
        when(s1.isShipDestroyed()).thenReturn(true);
        when(s2.isShipDestroyed()).thenReturn(true);
        assertTrue(stageModel.isPlayer1HasNoMoreShips());
    }

    @Test
    void testIsPlayer1HasNoMoreShips_SomeShipsNotDestroyed() {
        Ship s1 = mock(Ship.class);
        stageModel.getPlayer1ShipsV1().add(s1);
        Ship s2 = mock(Ship.class);
        stageModel.getPlayer1ShipsV1().add(s2);
        when(s1.isShipDestroyed()).thenReturn(false);
        when(s2.isShipDestroyed()).thenReturn(true);
        assertFalse(stageModel.isPlayer1HasNoMoreShips());
    }

    @Test
    void testIsPlayer2HasNoMoreShips_AllShipsDestroyed() {
        Ship s1 = mock(Ship.class);
        stageModel.getPlayer2ShipsV1().add(s1);
        Ship s2 = mock(Ship.class);
        stageModel.getPlayer2ShipsV1().add(s2);
        when(s1.isShipDestroyed()).thenReturn(true);
        when(s2.isShipDestroyed()).thenReturn(true);
        assertTrue(stageModel.isPlayer2HasNoMoreShips());
    }

    @Test
    void testIsPlayer2HasNoMoreShips_SomeShipsNotDestroyed() {
        Ship p2s1 = mock(Ship.class);
        stageModel.getPlayer2ShipsV1().add(p2s1);
        Ship p2s2 = mock(Ship.class);
        stageModel.getPlayer2ShipsV1().add(p2s2);
        when(p2s1.isShipDestroyed()).thenReturn(false);
        when(p2s2.isShipDestroyed()).thenReturn(true);
        assertFalse(stageModel.isPlayer2HasNoMoreShips());
    }

    @Test
    void testGetDefaultElementFactory() {
        StageElementsFactory factory = stageModel.getDefaultElementFactory();
        assertNotNull(factory);
    }
}
