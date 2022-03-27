package bep.game.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

import bep.game.domain.Game;

public class GameTest {
    @Test
    void EntityNoConstructorTest() {
        Constructor<?>[] constructors = Game.class.getConstructors();

        assertEquals(constructors.length, 1);
        assertEquals(constructors[0].getParameterCount(), 0);
    }
}
