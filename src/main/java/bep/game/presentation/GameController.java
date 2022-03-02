package bep.game.presentation;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bep.game.application.GameService;
import bep.game.application.PlayerService;
import bep.game.application.RoundService;
import bep.game.domain.Game;
import bep.game.domain.Round;
import bep.game.presentation.dto.GameDto;
import bep.game.presentation.dto.PlayerDto;
import bep.words.application.WordService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    RoundService roundService;

    @Autowired
    WordService wordService;

    @PostMapping
    public ResponseEntity<GameDto> start(@RequestBody PlayerDto playerDto) {
        var player = playerService.findOrCreate(playerDto.getUsername());
        // log.info("Player created {}", player);

        Game newGame = gameService.newGame(player);
        log.info("Created new game");
        // Round round = roundService.create(newGame);
        var word = wordService.provideRandomWord(5);
        log.info("Created new word");
        var round = new Round().setGame(newGame).setWord(word);
        log.info("Created new round");

        gameService.save(newGame);
        roundService.save(round);
        // var saveround = roundService.save(round);
        // log.info("round provided by round servcice {}", saveround.getId());

        HashMap<Integer, Character> peak = new HashMap<Integer, Character>();

        for (int i = 0; i < word.getLength(); i++) {
            peak.put(i, i == 0 ? word.getValue().charAt(i) : '*');
        }

        var body = new GameDto(newGame.getId(), player.getId(), newGame.getRoundCount(), peak);

        return ResponseEntity.ok().body(body);
    }
}
