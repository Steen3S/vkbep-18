package bep.game.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bep.game.data.GuessRepository;
import bep.game.domain.Guess;

@DataJpaTest
public class GuessServiceTest {
    @Autowired
    GuessRepository gr;

    @Test
    void saveGuess() throws Exception {
        Guess g = new Guess();
        g.setValue("beans");

        gr.save(g);
    }
}
