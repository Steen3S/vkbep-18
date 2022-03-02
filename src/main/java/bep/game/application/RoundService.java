package bep.game.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.RoundRepository;
import bep.game.domain.Round;

@Service
public class RoundService {
    @Autowired
    private RoundRepository roundRepository;

    public Round create() {
        var round = new Round();
        return round;
    }

    public Round save(Round round) {
        return roundRepository.save(round);
    }
}
