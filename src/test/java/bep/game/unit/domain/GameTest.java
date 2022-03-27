package bep.game.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

import bep.game.domain.Game;
import bep.game.domain.GameStatus;
import bep.game.domain.Round;
import bep.words.domain.Word;

class GameTest {
    @Test
    void EntityNoConstructorTest() {
        Constructor<?>[] constructors = Game.class.getConstructors();

        assertEquals(constructors.length, 1);
        assertEquals(constructors[0].getParameterCount(), 0);
    }

    @Test
    void calculateWordLength() {
        Game g = new Game();
        Round r = new Round();
        Word w = new Word("hello");

        r.setWord(w);
        g.addRound(r);

        assertEquals(g.calculateWordLength(), 6);
    }

    @Test
    void getCurrentRound() {
        Game g = new Game();
        Round r = new Round();
        Word w = new Word("hello");

        r.setWord(w);
        g.addRound(r);

        assertEquals(g.getCurrentRound(), r);
    }

    @Test
    void increaseScore() {
        Game g = new Game();
        Round r = new Round();
        Word w = new Word("hello");

        r.setWord(w);
        g.addRound(r);

        g.increaseScore(10.9);

        assertEquals(g.getScore(), 10.9);
    }

    @Test
    void endGame() {
        Game g = new Game();
        Round r = new Round();
        Word w = new Word("hello");

        r.setWord(w);
        g.addRound(r);

        g.endGame();

        assertEquals(g.getStatus(), GameStatus.DONE);
    }
}
