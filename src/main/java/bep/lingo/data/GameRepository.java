package bep.lingo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bep.lingo.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    
}