package bep.game.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import bep.game.presentation.dto.GameDto;
import bep.game.presentation.dto.PlayerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllGamesShouldReturnOkay() {
        String r = this.restTemplate.getForObject("http://localhost:" + port +
                "/game", String.class);

        log.info("returned string={}", r);
    }

    @Test
    public void newGameShouldReturnGameDto() {
        ResponseEntity<GameDto> r = this.restTemplate.postForEntity("http://localhost:" + port +
                "/game", new PlayerDto().setUsername("Dries"), GameDto.class);

        log.info("returned gameDto={}", r);
    }
}
