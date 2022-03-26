package bep.game.domain;

import java.util.ArrayList;
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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    double score;

    @ManyToOne(fetch = FetchType.LAZY)
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
        double score = this.score += s;
        log.info("New score is: {}", score);
        return score;

    }

    public void endGame() {
        this.status = GameStatus.DONE;
    }
}