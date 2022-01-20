package bep.words.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bep.words.application.WordService;
import bep.words.domain.exception.WordLengthNotSupportedException;

@RestController
@RequestMapping("words")
public class RandomWordController {
    private final WordService service;

    public RandomWordController(WordService service) {
        this.service = service;
    }

    @GetMapping("random")
    public String getRandomWord(@RequestParam Integer length) {
        try {
            return this.service.provideRandomWord(length);
        } catch (WordLengthNotSupportedException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}
