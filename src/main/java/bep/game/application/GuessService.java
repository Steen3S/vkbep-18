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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            currentRound.setStatus(RoundStatus.PASS); // Update status so round can't be guessed any more.
            currentRound.calcScore(); // Calc and update score prop.
        }
        if (currentRound.getGuesses().size() >= 4) {
            log.info("To many guessses");
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
