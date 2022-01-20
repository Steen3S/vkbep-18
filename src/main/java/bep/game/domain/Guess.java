package bep.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String value;

    @ManyToOne
    Round round;

    Status[] getStatus() {
        char[] chars = new char[round.word.getLength()];
        Status[] stats = new Status[round.word.getLength()];

        int index = 0;
        for (char ch : chars) {
            if (ch == value.charAt(index)) {
                stats[index] = Status.CORRECT;
            } else if (round.word.getValue().indexOf(ch) != -1) {
                stats[index] = Status.PRESENT;
            } else {
                stats[index] = Status.ABSENT;
            }
        }

        return stats;
    }
}

enum Status {
    CORRECT, PRESENT, ABSENT;
}
