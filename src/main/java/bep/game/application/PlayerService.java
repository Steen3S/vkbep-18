package bep.game.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.PlayerRepository;
import bep.game.domain.Player;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player findOrCreate(String name) {
        Optional<Player> player = playerRepository.findByName(name);

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
