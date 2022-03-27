package bep.game.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import bep.game.domain.Game;
import bep.game.domain.GameStatus;
import bep.game.domain.Round;
import bep.words.domain.Word;

class GameTest {
    Game g = new Game();
    Round r = new Round();
    Word w = new Word("hello");

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 }) // six numbers
    void giveLength(int expected) {

        for (int i = 0; i < expected; i++) {
            g.addRound(r);

        }
    }

    static Stream<Arguments> wordLengthArguments() {
        return Stream.of(
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(5));
    }

    @Test
    void EntityNoConstructorTest() {
        Constructor<?>[] constructors = Game.class.getConstructors();

        assertEquals(constructors.length, 1);
        assertEquals(constructors[0].getParameterCount(), 0);
    }

    @Test
    void calculateWordLength() {
        r.setWord(w);
        g.addRound(r);

        assertEquals(g.calculateWordLength(), 6);
    }

    @Test
    void getCurrentRound() {
        r.setWord(w);
        g.addRound(r);

        assertEquals(g.getCurrentRound(), r);
    }

    @Test
    void increaseScore() {
        r.setWord(w);
        g.addRound(r);

        g.increaseScore(10.9);

        assertEquals(g.getScore(), 10.9);
    }

    @Test
    void endGame() {

        r.setWord(w);
        g.addRound(r);

        g.endGame();

        assertEquals(g.getStatus(), GameStatus.DONE);
    }
}
