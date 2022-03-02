package bep.game.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    double score;

    @ManyToOne(cascade = CascadeType.ALL)
    Player player;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    public List<Round> rounds = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    public GameStatus status = GameStatus.PLAYING;

    public int calculateWordLength() {
        return rounds.size() % 3 + 5;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }

    public Round getCurrentRound() {
        return rounds.get(getRoundCount() - 1);
    }

    public int getRoundCount() {
        return rounds.size();
    }

    public double increaseScore(double s) {
        return this.score += s;
    }

    public void endGame() {
        this.status = GameStatus.DONE;
    }

}

enum GameStatus {
    PLAYING,
    DONE
}