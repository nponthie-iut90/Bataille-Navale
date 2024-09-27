package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ShipPartTest {

    private GameStageModel gameStageModel;
    private Ship parentShip;
    private ShipPart shipPart;

    @BeforeEach
    public void setUp() {
        gameStageModel = Mockito.mock(GameStageModel.class);
        parentShip = new Ship(gameStageModel, 3, "ParentShip", 1);
        shipPart = new ShipPart(1, parentShip, gameStageModel);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, shipPart.getColor());
        assertFalse(shipPart.isDestroyed());
        assertSame(parentShip, shipPart.getParentShip());
    }

    @Test
    public void testSetDestroyedAndIsDestroyed() {
        assertFalse(shipPart.isDestroyed());
        shipPart.setDestroyed();
        assertTrue(shipPart.isDestroyed());
    }
}
