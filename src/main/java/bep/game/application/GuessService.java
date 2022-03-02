package bep.game.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bep.game.data.GameRepository;
import bep.game.data.GuessRepository;
import bep.game.data.RoundRepository;
import bep.game.domain.Game;
import bep.game.domain.Guess;
import bep.game.domain.Round;
import bep.game.domain.RoundStatus;

@Service
public class GuessService {
    @Autowired
    private GuessRepository guessRepository;
    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private GameRepository gameRepository;

    public Guess makeGuess(Game game, String word) throws Exception {
        Round currentRound = game.getCurrentRound();

        if (currentRound.getStatus() != RoundStatus.OPEN) {
            throw new Exception("Round is already closed");
        }

        Guess guess = new Guess(currentRound);
        guess.setValue(word);
        currentRound.getGuesses().add(guess);

        if (guess.isCorrect()) {
            currentRound.setStatus(RoundStatus.PASS);
            game.increaseScore(5 * (game.getRoundCount() - 5) + 5);
        } else if (currentRound.getGuesses().size() >= 4) {
            game.endGame();
        }

        guessRepository.save(guess);
        roundRepository.save(currentRound);
        gameRepository.save(game);

        return guess;
    }

    public Guess save(Guess guess) {
        return guessRepository.save(guess);
    }
}
