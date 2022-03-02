package bep.game.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bep.game.domain.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

}