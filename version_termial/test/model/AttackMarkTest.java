package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackMarkTest {

    private GameStageModel gameStageModel;
    private AttackMark attackMark;

    @BeforeEach
    public void setUp() {
        gameStageModel = Mockito.mock(GameStageModel.class);
        attackMark = new AttackMark(gameStageModel, 1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, attackMark.getColor());
    }

    @Test
    public void testSetterAndGetter() {
        attackMark.setColor(2);
        assertEquals(2, attackMark.getColor());
    }
}
