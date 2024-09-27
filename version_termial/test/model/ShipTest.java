package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


public class ShipTest {

    private Ship ship;
    private ShipPart part1;
    private ShipPart part2;
    private ShipPart part3;
    private GameStageModel gameStageModel;

    @BeforeEach
    public void setUp() {
        gameStageModel = Mockito.mock(GameStageModel.class);
        part1 = Mockito.mock(ShipPart.class);
        part2 = Mockito.mock(ShipPart.class);
        part3 = Mockito.mock(ShipPart.class);
        ship = new Ship(gameStageModel, 3, "TestShip", 1);
        ship.setShipParts(0, part1);
        ship.setShipParts(1, part2);
        ship.setShipParts(2, part3);
    }

    @Test
    public void testGetName() {
        assertEquals("TestShip", ship.getName());
    }

    @Test
    public void testGetSize() {
        assertEquals(3, ship.getSize());
    }

    @Test
    public void testGetShipParts() {
        ShipPart[] parts = ship.getShipParts();
        assertEquals(3, parts.length);
        assertSame(part1, parts[0]);
        assertSame(part2, parts[1]);
        assertSame(part3, parts[2]);
    }

    @Test
    public void testGetShipPartByIndex() {
        assertSame(part1, ship.getShipParts(0));
        assertSame(part2, ship.getShipParts(1));
        assertSame(part3, ship.getShipParts(2));
    }

    @Test
    public void testSetShipParts() {
        ShipPart newPart = Mockito.mock(ShipPart.class);
        ship.setShipParts(1, newPart);
        assertSame(newPart, ship.getShipParts(1));
    }

    @Test
    public void testIsShipDestroyed_allPartsDestroyed() {
        Mockito.when(part1.isDestroyed()).thenReturn(true);
        Mockito.when(part2.isDestroyed()).thenReturn(true);
        Mockito.when(part3.isDestroyed()).thenReturn(true);
        assertTrue(ship.isShipDestroyed());
    }

    @Test
    public void testIsShipDestroyed_notAllPartsDestroyed() {
        Mockito.when(part1.isDestroyed()).thenReturn(true);
        Mockito.when(part2.isDestroyed()).thenReturn(false);
        Mockito.when(part3.isDestroyed()).thenReturn(true);
        assertFalse(ship.isShipDestroyed());
    }

    @Test
    public void testSetShipDestroyed() {
        ship.setShipDestroyed(true);
        assertTrue(ship.getIsShipDestroyed());

        ship.setShipDestroyed(false);
        assertFalse(ship.getIsShipDestroyed());
    }
}
