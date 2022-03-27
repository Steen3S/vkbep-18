package bep.game.unit.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bep.words.domain.Word;

public class WordTest {
    @Test
    @DisplayName("Test different word lengths")
    void assertLength() {
        Word word5 = new Word("ABCDE");
        assertEquals(word5.getLength(), 5);

        Word word6 = new Word("ABCDEF");
        assertEquals(word6.getLength(), 6);

        Word word7 = new Word("ABCDEFG");
        assertEquals(word7.getLength(), 7);
    }
}
