package bep.game.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bep.game.data.GameRepository;
import bep.game.domain.Game;
import bep.game.domain.Player;

@DataJpaTest
public class GameServiceTest {
    @Autowired
    GameRepository gr;

    @Test
    void saveGame() throws Exception {
        Player p = new Player();
        Game g = new Game();
        g.setPlayer(p);

        gr.save(g);
    }

    // @Test
    // void getAllReturnsArray() {
    // gs.getAll();
    // }

}
