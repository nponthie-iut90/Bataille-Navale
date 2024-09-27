package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BoardTest {

    private Board board;
    private GameStageModel gameStageModel;

    @BeforeEach
    public void setUp() {
        gameStageModel = Mockito.mock(GameStageModel.class);
        board = new Board(0, 0, gameStageModel);
    }

    @Test
    public void testAddShipToBoardHorizontal() {
        Ship ship = Mockito.mock(Ship.class);
        ShipPart part1 = Mockito.mock(ShipPart.class);
        ShipPart part2 = Mockito.mock(ShipPart.class);
        ShipPart part3 = Mockito.mock(ShipPart.class);
        when(ship.getSize()).thenReturn(3);
        when(ship.getShipParts(0)).thenReturn(part1);
        when(ship.getShipParts(1)).thenReturn(part2);
        when(ship.getShipParts(2)).thenReturn(part3);
        board.addShipToBoard(ship, 0, 0, true);
        verify(gameStageModel).putInContainer(part1, board, 0, 0);
        verify(gameStageModel).putInContainer(part2, board, 0, 1);
        verify(gameStageModel).putInContainer(part3, board, 0, 2);
    }

    @Test
    public void testAddShipToBoardVertical() {
        Ship ship = Mockito.mock(Ship.class);
        ShipPart part1 = Mockito.mock(ShipPart.class);
        ShipPart part2 = Mockito.mock(ShipPart.class);
        ShipPart part3 = Mockito.mock(ShipPart.class);
        when(ship.getSize()).thenReturn(3);
        when(ship.getShipParts(0)).thenReturn(part1);
        when(ship.getShipParts(1)).thenReturn(part2);
        when(ship.getShipParts(2)).thenReturn(part3);
        board.addShipToBoard(ship, 0, 0, false);
        verify(gameStageModel).putInContainer(part1, board, 0, 0);
        verify(gameStageModel).putInContainer(part2, board, 1, 0);
        verify(gameStageModel).putInContainer(part3, board, 2, 0);
    }

    @Test
    public void testAddMarkToBoard() {
        AttackMark attackMark = Mockito.mock(AttackMark.class);
        board.addMarkToBoard(attackMark, 5, 5);
        verify(gameStageModel).putInContainer(attackMark, board, 5, 5);
    }
}
