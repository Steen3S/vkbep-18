package bep.game.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bep.game.application.GameService;
import bep.game.application.GuessService;
import bep.game.application.PlayerService;
import bep.game.application.RoundService;
import bep.game.domain.Game;
import bep.game.domain.GameStatus;
import bep.game.domain.Guess;
import bep.game.domain.Round;
import bep.game.presentation.dto.GuessDto;
import bep.words.application.WordService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Slf4j
@RestController
@RequestMapping("/guess")
public class GuessController {
    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    GuessService guessService;

    @Autowired
    RoundService roundService;

    @Autowired
    WordService wordService;

    @PostMapping("/{gameId}")
    public ResponseEntity<?> guess(@PathVariable String gameId, @RequestBody GuessDto guessDto) {

        Game game = gameService.getGameById(Long.parseLong(gameId));
        log.info("gamestatus {}", game.getStatus());

        try {
            JSONObject obj = new JSONObject();
            if (game.getStatus() == GameStatus.DONE) {
                obj.put("message", "Game over! Please create a new game.");
                obj.put("score", game.getScore());
                return ResponseEntity.ok().body(obj);
            }

            Guess guess = guessService.makeGuess(game, guessDto.getGuess());

            if (guess.isCorrect()) {

                var word = wordService.provideRandomWord(game.calculateWordLength());

                Round nextRound = new Round();

                nextRound.setWord(word);
                nextRound.setGame(game);
                game.addRound(nextRound);

                roundService.save(nextRound);

                gameService.save(game);
                obj.put("next_word_peak", nextRound.peak());
                obj.put("message", "You did great!");
                obj.put("current_score", game.getScore());
                obj.put("guess_status", guess.getStatus());
            } else {
                obj.put("message", "Wrong answer, try again.");
                obj.put("guess_status", guess.getStatus());
                obj.put("guess_amount", game.getCurrentRound().getGuesses().size());
            }

            return ResponseEntity.ok().body(obj);

        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
