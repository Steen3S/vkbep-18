package bep.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String value;

    @ManyToOne
    Round round;

    public Guess(Round round) {
        this.round = round;
    }

    public GuessStatus[] getStatus() {
        GuessStatus[] stats = new GuessStatus[round.word.getLength()];
        String word = round.word.getValue();

        for (int i = 0; i < value.length(); i++) {
            if (word.charAt(i) == value.charAt(i)) {
                stats[i] = GuessStatus.CORRECT;
            } else if (word.contains(Character.toString(value.charAt(i)))) {
                stats[i] = GuessStatus.PRESENT;
            } else {
                stats[i] = GuessStatus.ABSENT;
            }
        }

        return stats;
    }

    public boolean isCorrect() {
        for (GuessStatus gs : getStatus()) {
            if (gs != GuessStatus.CORRECT) {
                return false;
            }
        }
        return true;
    }
}

enum GuessStatus {
    CORRECT, PRESENT, ABSENT;
}
