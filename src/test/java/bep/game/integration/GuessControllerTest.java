package bep.game.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import bep.game.presentation.dto.GuessDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GuessControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void makeGuessAndThrowNoError() {

        GuessDto gDto = new GuessDto();
        gDto.setGuess("beans");

        var r = this.restTemplate.postForEntity("http://localhost:" + port +
                "/guess", gDto, Object.class);

        log.info("returned string={}", r);
    }

    // @Test
    // public void newGameShouldReturnGameDto() throws Exception {
    // ResponseEntity<GameDto> r =
    // this.restTemplate.postForEntity("http://localhost:" + port +
    // "/guess", new PlayerDto().setUsername("Dries"), GameDto.class);

    // log.info("returned gameDto={}", r);
    // }
}
