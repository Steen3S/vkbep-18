package bep.game.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import bep.game.presentation.dto.GameDto;
import bep.game.presentation.dto.PlayerDto;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // @Test
    // public void startNewGame() {
    // GameDto r = this.restTemplate.postForObject("http://localhost:" + port +
    // "/game",
    // new PlayerDto().setUsername("dries"), GameDto.class);
    // }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String r = this.restTemplate.getForObject("http://localhost:" + port +
                "/game",
                String.class);

        log.info("returned string={}", r);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                "/game",
                String.class)).isNotNull();
    }
}
