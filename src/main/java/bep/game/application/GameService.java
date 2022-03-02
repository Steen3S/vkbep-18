package bep.game.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.GameRepository;
import bep.game.domain.Game;
import bep.game.domain.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game newGame(Player player) {
        var game = new Game().setPlayer(player);
        return game;
    }

    public Game getGameById(Long id) {
        log.info("Getting game by id {}", id);
        return this.gameRepository.getById(id);
    }

    public Game save(Game game) {
        return this.gameRepository.save(game);
    }
}
