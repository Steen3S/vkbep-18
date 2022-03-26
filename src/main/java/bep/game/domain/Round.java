package bep.game.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bep.words.domain.Word;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Data
@Accessors(chain = true)
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Enumerated(EnumType.STRING)
    RoundStatus status = RoundStatus.OPEN;

    @OneToOne
    Word word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Game game;

    @OneToMany
    List<Guess> guesses = new ArrayList<>();

    public HashMap<Integer, Character> peak() {
        HashMap<Integer, Character> p = new HashMap<Integer, Character>();

        for (int i = 0; i < word.getLength(); i++) {
            p.put(i, i == 0 ? word.getValue().charAt(i) : '*');
        }

        return p;
    }

    public void calcScore() {
        int score = 5 * (5 - game.getRoundCount()) + 5;
        game.increaseScore(score);
    }

}
