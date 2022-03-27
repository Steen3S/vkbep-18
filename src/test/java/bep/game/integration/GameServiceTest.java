package bep.game.integration;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bep.game.application.GameService;
import bep.game.domain.Player;

public class GameServiceTest {
    Player p1;

    @BeforeEach
    void init() {
        p1 = new Player();
        p1.setName("P1");
    }

    @Test
    void startGame() {
        GameService gas = mock(GameService.class);
        gas.newGame(p1);
    }
}
