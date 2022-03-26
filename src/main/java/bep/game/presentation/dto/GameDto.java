package bep.game.presentation.dto;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GameDto {
    Long game_id;
    Long player_id;

    int round_number;
    HashMap<Integer, Character> peak;
}
