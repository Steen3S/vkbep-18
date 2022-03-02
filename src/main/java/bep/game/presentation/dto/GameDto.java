package bep.game.presentation.dto;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class GameDto {
    Long game_id;
    Long player_id;

    int round_number;
    HashMap<Integer, Character> peak;
}
