package bep.game.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bep.game.domain.Guess;

@Repository
public interface GuessRepository extends JpaRepository<Guess, Long> {

}