package bep.game.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    double score;

    @ManyToOne
    Player player;

    @Enumerated(EnumType.STRING)
    public GameStatus status;

}

enum GameStatus {
    PLAYING,
    DONE
}