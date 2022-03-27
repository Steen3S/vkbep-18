package bep.game.unit.presentation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bep.game.application.GameService;
import bep.game.application.PlayerService;
import bep.game.domain.Game;
import bep.game.domain.Player;

@SpringBootTest
public class GameServiceTest {
    @Autowired
    PlayerService ps;

    @Autowired
    GameService gs;

    @Test
    void newGame() {
        Player p = ps.findOrCreate("HU");
        Game g = gs.newGame(p);
        g = gs.save(g);

        Game rG = gs.getGameById(g.getId());

        assertNotNull(rG);
    }
}
