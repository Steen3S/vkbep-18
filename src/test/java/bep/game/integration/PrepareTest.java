package bep.game.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bep.words.data.SpringWordRepository;
import bep.words.domain.Word;

@Configuration
public class PrepareTest {
    @Autowired
    SpringWordRepository ws;

    @Bean
    void SaveDummyWords() {
        ws.save(new Word("beans"));
        ws.save(new Word("spring"));
        ws.save(new Word("whaamed"));

        ws.flush();
    }
}
