package bep.game.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bep.game.application.GameService;
import bep.game.application.PlayerService;
import bep.game.application.RoundService;
import bep.game.domain.Game;
import bep.game.presentation.dto.GameDto;
import bep.game.presentation.dto.PlayerDto;
import bep.words.application.WordService;

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

    @GetMapping
    public ResponseEntity<List<GameDto>> getAll() {
        List<GameDto> response = new ArrayList<GameDto>();

        for (Game g : gameService.getAll()) {
            GameDto gdto = new GameDto(g.getId(), g.getPlayer().getId(), g.getRoundCount(), g.getCurrentRound().peak());
            response.add(gdto);
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<GameDto> start(@RequestBody PlayerDto playerDto) {
        var player = playerService.findOrCreate(playerDto.getUsername());
        // log.info("Player created {}", player);

        Game newGame = gameService.newGame(player);

        // Round round = roundService.create(newGame);
        var word = wordService.provideRandomWord(5);
        var round = roundService.create(newGame, word);

        gameService.save(newGame);
        roundService.save(round);

        GameDto response = new GameDto(newGame.getId(), player.getId(), newGame.getRoundCount(),
                newGame.getCurrentRound().peak());

        return ResponseEntity.ok().body(response);
    }
}
