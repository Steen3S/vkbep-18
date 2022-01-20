package bep.game.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import bep.words.domain.Word;

@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long roundCount;

    @OneToOne
    Word word;

    @ManyToOne
    Game game;

    @OneToMany
    List<Guess> guesses = new ArrayList<>();
}
