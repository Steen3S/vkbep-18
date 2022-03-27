package bep.game.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.RoundRepository;
import bep.game.domain.Game;
import bep.game.domain.Round;
import bep.words.domain.Word;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoundService {
    @Autowired
    private RoundRepository roundRepository;

    public Round create(Game game, Word word) {
        var round = new Round();
        round.setGame(game);
        round.setWord(word);
        log.info("Created new round! id: {}", round.getId());

        return round;
    }

    public Round save(Round round) {
        return roundRepository.save(round);
    }
}
