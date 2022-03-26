package bep.words.application;

import org.springframework.stereotype.Service;

import bep.words.data.SpringWordRepository;
import bep.words.domain.Word;
import bep.words.domain.exception.WordLengthNotSupportedException;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class WordService {
    private final SpringWordRepository wordRepository;

    public WordService(SpringWordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word provideRandomWord(Integer length) {
        String value = this.wordRepository
                .findRandomWordByLength(length)
                .orElseThrow(() -> new WordLengthNotSupportedException(length))
                .getValue();

        log.info("Created new word: {}", value);

        return new Word(value);
    }
}
