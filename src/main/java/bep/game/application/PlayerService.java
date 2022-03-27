package bep.game.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.PlayerRepository;
import bep.game.domain.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player findOrCreate(String name) {
        Optional<Player> player = playerRepository.findByName(name);
        log.info("Player {}", player);

        if (player.isPresent()) {
            return player.get();
        } else {
            var p = new Player();
            p.setName(name);
            return playerRepository.save(p);
        }

    }

    public Player getByName(String name) {
        return playerRepository.getByName(name);
    }
}
